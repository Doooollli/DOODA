package com.dooda.project.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BrdcHeader implements Serializable{
	
	private String version;				// Rinex Format Version
	private double[] ion_alpha;			// Ion Alpha (A0~A3)
	private double[] ion_beta;			// Ion Beta (B0~B3)
	private double a0_polynomial_term;	// Almanac parameter to compute time in UTC : A0
	private double a1_polynomial_term;	// Almanac parameter to compute time in UTC : A1
	private int reference_time;			// Reference time for UTC data
	private int week_number;			// UTC reference week number
	private int leap_seconds;			// Number of leap seconds
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public double[] getIon_alpha() {
		return ion_alpha;
	}
	public void setIon_alpha(double[] ion_alpha) {
		this.ion_alpha = ion_alpha;
	}
	public double[] getIon_beta() {
		return ion_beta;
	}
	public void setIon_beta(double[] ion_beta) {
		this.ion_beta = ion_beta;
	}
	public double getA0_polynomial_term() {
		return a0_polynomial_term;
	}
	public void setA0_polynomial_term(double a0_polynomial_term) {
		this.a0_polynomial_term = a0_polynomial_term;
	}
	public double getA1_polynomial_term() {
		return a1_polynomial_term;
	}
	public void setA1_polynomial_term(double a1_polynomial_term) {
		this.a1_polynomial_term = a1_polynomial_term;
	}
	public int getReference_time() {
		return reference_time;
	}
	public void setReference_time(int reference_time) {
		this.reference_time = reference_time;
	}
	public int getWeek_number() {
		return week_number;
	}
	public void setWeek_number(int week_number) {
		this.week_number = week_number;
	}
	public int getLeap_seconds() {
		return leap_seconds;
	}
	public void setLeap_seconds(int leap_seconds) {
		this.leap_seconds = leap_seconds;
	}
	
	@Override
	public String toString() {
		return "NavigationHeader [version=" + version + ", ion_alpha=" + Arrays.toString(ion_alpha) + ", ion_beta="
				+ Arrays.toString(ion_beta) + ", a0_polynomial_term=" + a0_polynomial_term + ", a1_polynomial_term="
				+ a1_polynomial_term + ", reference_time=" + reference_time + ", week_number=" + week_number
				+ ", leap_seconds=" + leap_seconds + "]";
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
