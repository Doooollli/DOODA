package com.dooda.project.domain;

import java.io.Serializable;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BrdcData implements Serializable {

	private int satellite_prn_number;	// Satellite PRN Number
	private String epoch;				// Epoch of current Satellite PRN Number
	private String sv_clock_bias;		// Space Vehicle clock bias (Seconds)
	private String sv_clock_drift;		// Space Vehicle clock drift (Seconds/Seconds)
	private String sv_clock_drift_rate;	// Space Vehicle clock drift rate (Seconds/Seconds^2)
	private String iode;				// Issue Of Date Ephemeris
	private String crs;					// Radius Correction Sinus Component (Meters)
	private String delta_n;				// Delta n (Radians/Seconds)
	private String mo;					// Mo Angle (Radians)
	private String cuc;					// Latitude Correction Cosinus Component (Radians)
	private String eccentricity;		// Orbit Eccentricity
	private String cus;					// Latitude Correction Sinus Component (Radians)
	private String sqrt_a;				// Square root of the orbit semi major axis (Meters^0.5)
	private String toe;					// Time Of Ephemeris (Seconds of GPS Week)
	private String cic;					// Inclination Correction Cosinus Component (Radians)
	private String big_omega;			// OMEGA Angle (Radians)
	private String cis;					// Angular Velocity (Radians)
	private String io;					// Initial Inclination (Radians)
	private String crc;					// Radius Correction Cosinus Component (Meters)
	private String little_omega;		// Omega Angle (Radians)
	private String omega_dot;			// Angular Velocity (Radians/Seconds)
	private String idot;				// Inclination Rate (Radians/Seconds)
	private String l2_code_channel;		// Codes on L2 channel
	private String gps_week;			// GPS Week Number (to go with TOE)
	private String l2_p_data_flag;		// L2 P data flag
	private String sv_accuraccy;		// Space Vehicle Accuraccy (Meters)
	private String sv_health;			// The health of the signal components
	private String tgd;					// Total Group Delay (Seconds)
	private String iodc;				// Issue of Data Clock
	private String transmission_time;	// Transmission Time of the Message (Seconds of GPS Week)
	private String interval;			// (see ICD-GPS-200, 20.3.3.4.3.1): (Hours)
	
	public int getSatellite_prn_number() {
		return satellite_prn_number;
	}
	public void setSatellite_prn_number(int satellite_prn_number) {
		this.satellite_prn_number = satellite_prn_number;
	}
	public String getEpoch() {
		return epoch;
	}
	public void setEpoch(String epoch) {
		this.epoch = epoch;
	}
	public String getSv_clock_bias() {
		return sv_clock_bias;
	}
	public void setSv_clock_bias(String sv_clock_bias) {
		this.sv_clock_bias = sv_clock_bias;
	}
	public String getSv_clock_drift() {
		return sv_clock_drift;
	}
	public void setSv_clock_drift(String sv_clock_drift) {
		this.sv_clock_drift = sv_clock_drift;
	}
	public String getSv_clock_drift_rate() {
		return sv_clock_drift_rate;
	}
	public void setSv_clock_drift_rate(String sv_clock_drift_rate) {
		this.sv_clock_drift_rate = sv_clock_drift_rate;
	}
	public String getIode() {
		return iode;
	}
	public void setIode(String iode) {
		this.iode = iode;
	}
	public String getCrs() {
		return crs;
	}
	public void setCrs(String crs) {
		this.crs = crs;
	}
	public String getDelta_n() {
		return delta_n;
	}
	public void setDelta_n(String delta_n) {
		this.delta_n = delta_n;
	}
	public String getMo() {
		return mo;
	}
	public void setMo(String mo) {
		this.mo = mo;
	}
	public String getCuc() {
		return cuc;
	}
	public void setCuc(String cuc) {
		this.cuc = cuc;
	}
	public String getEccentricity() {
		return eccentricity;
	}
	public void setEccentricity(String eccentricity) {
		this.eccentricity = eccentricity;
	}
	public String getCus() {
		return cus;
	}
	public void setCus(String cus) {
		this.cus = cus;
	}
	public String getSqrt_a() {
		return sqrt_a;
	}
	public void setSqrt_a(String sqrt_a) {
		this.sqrt_a = sqrt_a;
	}
	public String getToe() {
		return toe;
	}
	public void setToe(String toe) {
		this.toe = toe;
	}
	public String getCic() {
		return cic;
	}
	public void setCic(String cic) {
		this.cic = cic;
	}
	public String getBig_omega() {
		return big_omega;
	}
	public void setBig_omega(String big_omega) {
		this.big_omega = big_omega;
	}
	public String getCis() {
		return cis;
	}
	public void setCis(String cis) {
		this.cis = cis;
	}
	public String getIo() {
		return io;
	}
	public void setIo(String io) {
		this.io = io;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public String getLittle_omega() {
		return little_omega;
	}
	public void setLittle_omega(String little_omega) {
		this.little_omega = little_omega;
	}
	public String getOmega_dot() {
		return omega_dot;
	}
	public void setOmega_dot(String omega_dot) {
		this.omega_dot = omega_dot;
	}
	public String getIdot() {
		return idot;
	}
	public void setIdot(String idot) {
		this.idot = idot;
	}
	public String getL2_code_channel() {
		return l2_code_channel;
	}
	public void setL2_code_channel(String l2_code_channel) {
		this.l2_code_channel = l2_code_channel;
	}
	public String getGps_week() {
		return gps_week;
	}
	public void setGps_week(String gps_week) {
		this.gps_week = gps_week;
	}
	public String getL2_p_data_flag() {
		return l2_p_data_flag;
	}
	public void setL2_p_data_flag(String l2_p_data_flag) {
		this.l2_p_data_flag = l2_p_data_flag;
	}
	public String getSv_accuraccy() {
		return sv_accuraccy;
	}
	public void setSv_accuraccy(String sv_accuraccy) {
		this.sv_accuraccy = sv_accuraccy;
	}
	public String getSv_health() {
		return sv_health;
	}
	public void setSv_health(String sv_health) {
		this.sv_health = sv_health;
	}
	public String getTgd() {
		return tgd;
	}
	public void setTgd(String tgd) {
		this.tgd = tgd;
	}
	public String getIodc() {
		return iodc;
	}
	public void setIodc(String iodc) {
		this.iodc = iodc;
	}
	public String getTransmission_time() {
		return transmission_time;
	}
	public void setTransmission_time(String transmission_time) {
		this.transmission_time = transmission_time;
	}
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	
	@Override
	public String toString() {
		return "NavigationData [satellite_prn_number=" + satellite_prn_number + ", epoch=" + epoch + ", sv_clock_bias="
				+ sv_clock_bias + ", sv_clock_drift=" + sv_clock_drift + ", sv_clock_drift_rate=" + sv_clock_drift_rate
				+ ", iode=" + iode + ", crs=" + crs + ", delta_n=" + delta_n + ", mo=" + mo + ", cuc=" + cuc
				+ ", eccentricity=" + eccentricity + ", cus=" + cus + ", sqrt_a=" + sqrt_a + ", toe=" + toe + ", cic="
				+ cic + ", big_omega=" + big_omega + ", cis=" + cis + ", io=" + io + ", crc=" + crc + ", little_omega="
				+ little_omega + ", omega_dot=" + omega_dot + ", idot=" + idot + ", l2_code_channel=" + l2_code_channel
				+ ", gps_week=" + gps_week + ", l2_p_data_flag=" + l2_p_data_flag + ", sv_accuraccy=" + sv_accuraccy
				+ ", sv_health=" + sv_health + ", tgd=" + tgd + ", iodc=" + iodc + ", transmission_time="
				+ transmission_time + ", interval=" + interval + "]";
	}
	
	public String toJackson() {
    	JsonObject jo = new JsonObject();
    	jo.addProperty("@class", this.getClass().getName());

    	Gson gson = new Gson();
    	JsonElement je = gson.toJsonTree(this);
    	
    	for (Map.Entry<String, JsonElement> entry : ((JsonObject) je).entrySet()) {
    		jo.add(entry.getKey(), entry.getValue());
    	}
    	
    	return jo.toString();
	}

}
