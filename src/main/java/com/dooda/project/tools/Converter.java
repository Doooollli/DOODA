package com.dooda.project.tools;

import java.time.LocalDateTime;

import com.dooda.project.domain.BrdcData;
import com.dooda.project.domain.BrdcHeader;
import com.pps.gpsbrdcparser.Navigation.Brdc;
import com.pps.gpsbrdcparser.Navigation.Header;


	public class Converter {

		public static Header toOriginHeader(BrdcHeader h) {
			return new Header(h.getVersion(), 
					h.getIon_alpha()[0], 
					h.getIon_alpha()[1],
					h.getIon_alpha()[2], 
					h.getIon_alpha()[3], 
					h.getIon_beta()[0], 
					h.getIon_beta()[1], 
					h.getIon_beta()[2], 
					h.getIon_beta()[3], 
					h.getA0_polynomial_term(),
					h.getA1_polynomial_term(), 
					h.getReference_time(), 
					h.getWeek_number(), 
					h.getLeap_seconds());
		}
		
		public static BrdcHeader toHeader(Header header) {
			
			BrdcHeader h = new BrdcHeader();
			h.setVersion(header.getVersion());
			h.setIon_alpha(header.getIonAlpha());
			h.setIon_beta(header.getIonBeta());
			h.setA0_polynomial_term(header.getA0PolynomialTerm());
			h.setA1_polynomial_term(header.getA1PolynomialTerm());
			h.setReference_time(header.getReferenceTime());
			h.setWeek_number(header.getWeekNumber());
			h.setLeap_seconds(header.getLeapSeconds());
			
			return h;
		}
		
		public static Brdc toOriginData(BrdcData d) {
			return new Brdc(d.getSatellite_prn_number(), 
					LocalDateTime.parse( d.getEpoch() ),
					d.getSv_clock_bias(),
					d.getSv_clock_drift(),
					d.getSv_clock_drift_rate(),
					d.getIode(),
					d.getCrs(),
					d.getDelta_n(), 
					d.getMo(),
					d.getCuc(),
					d.getEccentricity(),
					d.getCus(), 
					d.getSqrt_a(), 
					d.getToe(), 
					d.getCic(),
					d.getBig_omega(),
					d.getCis(),
					d.getIo(), 
					d.getCrc(), 
					d.getLittle_omega(), 
					d.getOmega_dot(), 
					d.getIdot(), 
					d.getL2_code_channel(),
					d.getGps_week(),
					d.getL2_p_data_flag(),
					d.getSv_accuraccy(), 
					d.getSv_health(), 
					d.getTgd(),
					d.getIodc(),
					d.getTransmission_time(),
					d.getInterval());
		}
		
		public static BrdcData toData(Brdc data) {
			
			BrdcData d = new BrdcData();
			d.setSatellite_prn_number(data.getSatellite_prn_number());
			d.setEpoch(data.getEpoch().toString());
			d.setSv_clock_bias(data.getSv_clock_bias());
			d.setSv_clock_drift(data.getSv_clock_drift());
			d.setSv_clock_drift_rate(data.getSv_clock_drift_rate());
			d.setIode(data.getIode());
			d.setCrs(data.getCrs());
			d.setDelta_n(data.getDelta_n());
			d.setMo(data.getMo());
			d.setCuc(data.getCuc());
			d.setEccentricity(data.getEccentricity());
			d.setCus(data.getCus());
			d.setSqrt_a(data.getSqrt_a());
			d.setToe(data.getToe());
			d.setCic(data.getCic());
			d.setBig_omega(data.getBig_omega());
			d.setCis(data.getCis());
			d.setIo(data.getIo());
			d.setCrc(data.getCrc());
			d.setLittle_omega(data.getLittle_omega());
			d.setOmega_dot(data.getOmega_dot());
			d.setIdot(data.getIdot());
			d.setL2_code_channel(data.getL2_code_channel());
			d.setGps_week(data.getGps_week());
			d.setL2_p_data_flag(data.getL2_p_data_flag());
			d.setSv_accuraccy(data.getSv_accuraccy());
			d.setSv_health(data.getSv_health());
			d.setTgd(data.getTgd());
			d.setIodc(data.getIodc());
			d.setTransmission_time(data.getTransmission_time());
			d.setInterval(data.getInterval());
			
			return d;
		}
		

}
