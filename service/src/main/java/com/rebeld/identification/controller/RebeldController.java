package com.rebeld.identification.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rebeld.identification.domain.RebeldInformation;
import com.rebeld.identification.exception.RebeldException;
import com.rebeld.identification.service.RebeldService;

/**
 * RestFull controller for rebels service. 
 * 
 * @author krlsMM
 */
@RestController
@RequestMapping("/api")
public class RebeldController {
	
	/**
	 * Rebels service.
	 */
	@Autowired
	private RebeldService rebeldService;
	
	/**
	 * Register the rebelds identification on any planet. 
	 * @param information {@link List} of {@link RebeldInformation}
	 * @return <code>true</code> if register will be right, <code>false</code> in other case.
	 */
	@RequestMapping(value = "/registerRebeld", method = RequestMethod.POST)
	@ResponseBody
	@ExceptionHandler({RebeldException.class})
	public CompletableFuture<Boolean> registerRebeld(@RequestBody List<RebeldInformation> information) {
		return rebeldService.registerRebeld(information);
	}
}
