package com.dooda.project;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.dooda.project.redis_db.GpsObserver2DAO;
import com.dooda.project.redis_db.JedisHelper;
import com.pps.gpsbrdcparser.Navigation;
import com.pps.gpsbrdcparser.Navigation.Brdc;
import com.pps.gpsbrdcparser.Navigation.Header;



public class App 
{

	public static Logger logger;
	public static LocalDateTime now_utc = LocalDateTime.now(Clock.systemUTC());
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
    public static void main( String[] args )
    {
    	
    	FTPClient ftp = null;
    	InputStream stream = null;
    	
    	try {
			logger = Logger.getLogger(com.dooda.project.App.class);
			
			// Running time checking
			logger.info("Run application - dooda_project");
	
			// FTP Client Example
			ftp = new FTPClient();
			ftp.connect("ftp.glonass-iac.ru");
	
			// Check FTP Server
			if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				ftp.disconnect();
				logger.fatal("FTP server refused connection.");
				return;
			}
			
			// ready to use FTP
			ftp.login("anonymous", "com.tuiddy.gpsobserver");
			ftp.changeWorkingDirectory("/MCC/BRDC/");

			// Change directory
			LocalDateTime focuseTime = now_utc;
			if (!ftp.changeWorkingDirectory(focuseTime.getYear() + "/"))
				throw new FileNotFoundException(ftp.printWorkingDirectory() + focuseTime.getYear() + "/ : does not exist");

			// Get file list
			FTPFile[] files = ftp.listFiles();
			

			
			FTPFile latest = null;
			for(int i=0; i<files.length; i++) {
				// GPS navigation file.
				if (files[i].getName().substring(0, 4).equals("BRDC"))
					latest = files[i];
			}
			//for(FTPFile ftpFile : files){
			//	if(ftpFile.getName().substring(0,4).equals("BRDC"))
			//		latest = ftpFile;
			//}
			
			
			// Failed find BRDC file
			if (latest == null)
				throw new FileNotFoundException(ftp.printWorkingDirectory() + " : BRDC file does not exist");
			
			logger.info(latest.getName() + " : ready to parse this file.");
			
			// Push data file stream
			stream = ftp.retrieveFileStream(latest.getName());
			pushDataStream(stream);
			
			
    	} catch (Exception e) {
    		// Error
//    		logger.fatal(e.toString());
    		e.printStackTrace();
    		
    	} finally {
			try {
				// Running time checking
				logger.info("Exit application - dooda_project");
				
				// return connection
				JedisHelper.getInstance().destroyPool();
				
				// Stream close
				if (stream != null) stream.close();
				
				// FTP Disconnect
				ftp.logout();
				ftp.disconnect();
				
			} catch (Exception e) {
			}
    	}
    }
    
    /**
     * FTP서버에서 데이터 파일스트림을 얻어와서 해당파일의 현재 시각와 가장 가까운 데이터를 Redis server에 업로드
     * @param stream : FTP 서버의 파일에서 나오는 입력 스트림
     * @throws FileNotFoundException : 파싱할 때 발생할 수 있음 (서버 확인)
     * @throws UnsupportedEncodingException : 파싱할 때 발생할 수 있음 (데이터 확인)
     */
    public static void pushDataStream(InputStream stream) throws FileNotFoundException, UnsupportedEncodingException {
    	// Parse file, divide header and data
    	Navigation navigation = Navigation.parse(stream);
    	Header header = navigation.getHeader();
    	Iterator<Brdc> data_list = navigation.iterator();
    	
    	// gpsobserver2 data access object
    	GpsObserver2DAO dao = new GpsObserver2DAO();
    	
    	// BRDC array
    	Brdc[] closest = new Brdc[33];

    	
    	// Flags to log
    	boolean upToDate = true;
    	boolean[] updateFlag = new boolean[33];
    	Arrays.fill(updateFlag, false);
    	
    	// Load current data
    	for (int i = 1; i < closest.length; i++)
    		// failed : set null
    		closest[i] = dao.findBrdcByPrn(i);
    	
    	// search closest data each PRN
    	while (data_list.hasNext()) {
    		
    		Brdc data = data_list.next();
    		int prn = data.getSatellite_prn_number();
    		
    		if (closest[prn] == null || 
    				timeTermAtNowUTC(data.getEpoch()) < timeTermAtNowUTC(closest[prn].getEpoch())) {

    			closest[prn] = data;
    			updateFlag[prn] = true;
    		}
    
    		
    	}
		
    	// Insert(Update) header in Redis server
    	dao.pushHeader(header);
    	
    	// Insert(Update) data in Redis server, logging update
		for (int i = 1; i < closest.length; i++) {
			if (closest[i] != null)
				dao.pushData(closest[i].getSatellite_prn_number(), closest[i]);
			
			if (updateFlag[i]) {
				upToDate = false;
        		logger.info("UPDATE : prn=" + closest[i].getSatellite_prn_number()
    						+ ", epoch=" + closest[i].getEpoch());
			}
		}
		
		if (upToDate) logger.info("Already up-to-date.");
    	
    }
    
    /**
     * 현재 시간과 입력한 시간의 절대적 차이값을 반환
     * @param arg0 : 무엇인진 모르겠지만 저것을 implements한 시간 객체
     * @return 현재 시간과의 절대적 차이값을 반환 (밀리초)
     */
    private static long timeTermAtNowUTC(ChronoLocalDateTime<?> arg0) {
    	return Math.abs(Duration.between(arg0, now_utc).toMillis());
    }
}
