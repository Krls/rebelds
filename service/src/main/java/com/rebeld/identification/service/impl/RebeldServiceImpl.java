package com.rebeld.identification.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rebeld.identification.domain.RebeldInformation;
import com.rebeld.identification.exception.RebeldException;
import com.rebeld.identification.service.RebeldService;

/**
 * {@link RebeldService} implementation.
 * @author krlsMM
 */
@Service
public class RebeldServiceImpl implements RebeldService {
	
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
	
	private Logger logger = LogManager.getLogger(RebeldServiceImpl.class);
	/* (non-Javadoc)
	 * @see com.rebeld.identification.service.impl.RebeldService#registerRebeld(java.util.List)
	 */
	@Override
	@Async
	public CompletableFuture<Boolean> registerRebeld(List<RebeldInformation> rebeldInformation) {
		try {
			logger.info("registerRebeld method is called");
			
			StringBuffer buf = new StringBuffer();
			for(RebeldInformation rebeld : rebeldInformation){
				generateRebeldLog(buf, rebeld);
			}
			boolean result = registerRebeldOnFile(buf);
			
			logger.info("registerRebeld method is finished with result " + result);
			
			return CompletableFuture.completedFuture(result);
		}catch (Exception e) {
			logger.error("There are any problem to register rebeld." + e.getMessage());
			CompletableFuture<Boolean> badResult = new CompletableFuture<>();
			badResult.completeExceptionally(new RebeldException("There are any problem to register rebeld."));
			return badResult;
		}
	}

	/**
	 * Register the String with all of rebels information to rebelds file.
	 * @param buf {@link StringBuffer} with rebleds information.
	 */
	private boolean registerRebeldOnFile(StringBuffer buf) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(rebeldsFile, true));
			writer.append(buf.toString());
			writer.close();
			return true;
		} catch (IOException e) {
			logger.error("There are any problem to accest to rebelds file." + e.getMessage());
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
