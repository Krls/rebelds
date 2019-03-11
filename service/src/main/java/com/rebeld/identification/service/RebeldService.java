package com.rebeld.identification.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rebeld.identification.domain.RebeldInformation;
import com.rebeld.identification.exception.RebeldException;

/**
 * The service that manage rebeld information.
 * @author krlsMM
 */
@Service
public class RebeldService {
	
	/**
	 * Static constants.
	 */
	private static final String BR = "\n";
	private static final String CONNECTOR_2 = " at ";
	private static final String CONNECTOR_1 = " on ";
	private static final String REBELD = "rebeld ";
	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * Rebels data file.
	 */
	@Value("${rebelds.register.file}")
	private String rebeldsFile;
	
	/**
	 * Register the rebel information
	 * 
	 * @param rebeldInformation List of {@link RebeldInformation}
	 * @return True if 
	 */
	public Boolean registerRebeld(List<RebeldInformation> rebeldInformation) {
		
		StringBuffer buf = new StringBuffer();
		for(RebeldInformation rebeld : rebeldInformation){
			generateRebeldLog(buf, rebeld);
		}		
		registerRebeldOnFile(buf);
		return true;
	}

	/**
	 * Register the String with all of rebels information to rebelds file.
	 * @param buf {@link StringBuffer} with rebleds information.
	 */
	private void registerRebeldOnFile(StringBuffer buf) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(rebeldsFile, true));
			writer.append(buf.toString());
			writer.close();
		} catch (IOException e) {
			throw new RebeldException("There are any problem to accest to rebelds file.");
		}
	}

	/**
	 * Format the information of the rebels.
	 * @param buf {@link StringBuffer} to populate
	 * @param rebeld {@link RebeldInformation} 
	 */
	private void generateRebeldLog(StringBuffer buf, RebeldInformation rebeld) {
		SimpleDateFormat smp = new SimpleDateFormat(DATE_FORMAT);
		buf.append(REBELD);
		buf.append(rebeld.getName());
		buf.append(CONNECTOR_1);
		buf.append(rebeld.getPlanet());
		buf.append(CONNECTOR_2);
		buf.append(smp.format(new Date()));
		buf.append(BR);
	}
}
