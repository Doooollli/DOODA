package com.dooda.project.redis_db;

import redis.clients.jedis.Jedis;

import com.dooda.project.domain.BrdcData;
import com.dooda.project.tools.Converter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pps.gpsbrdcparser.Navigation.Brdc;
import com.pps.gpsbrdcparser.Navigation.Header;



public class GpsObserver2DAO {
	private static final String KEY_GPS_BRDC = "gps:brdc:prn";
	private static final String KEY_GPS_HEADER = "gps:brdc:header";
	private Jedis jedis;
	
	public GpsObserver2DAO() {
		jedis = JedisHelper.getInstance().getResource();
	}
	
	/**
	 * Redis server에 저장된 BRDC data를 PRN으로 가져옴
	 * @param prn 
	 * @return success : BRDC Object, fail : null
	 */
	public Brdc findBrdcByPrn(int prn) {
		// target key setting
		String targetKey = KEY_GPS_BRDC + prn;
		
		// read data in Redis
		try {
			// json to Brdc object
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			BrdcData data = mapper.readValue(jedis.get(targetKey).toString(), BrdcData.class);
			return Converter.toOriginData(data);
			
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Brdc 데이터를  JSON형태로 저장
	 * @param prn 저장시에 Key 구분
	 * @param brdc 입력 데이터
	 * @return
	 */
	public void pushData(int prn, Brdc brdc) {
		// target key setting
		String targetKey = KEY_GPS_BRDC + prn;
		jedis.set(targetKey, Converter.toData(brdc).toJackson());
	}
	
	/**
	 * 해더 데이터를  JSON형태로 저장
	 * @param header 저장될 데이터
	 * @return
	 */
	public void pushHeader(Header header) {
		jedis.set(KEY_GPS_HEADER, Converter.toHeader(header).toJackson());
	}
	

}
