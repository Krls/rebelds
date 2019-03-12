package com.rebeld.identification.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.rebeld.identification.domain.RebeldInformation;

/**
 * The service that manage rebeld information.
 * 
 * @author krlsMM
 */
public interface RebeldService {

	/**
	 * Register the rebel information
	 * 
	 * @param rebeldInformation List of {@link RebeldInformation}
	 * @return <code>true</code> if register will be right.
	 */
	CompletableFuture<Boolean> registerRebeld(List<RebeldInformation> rebeldInformation);

}