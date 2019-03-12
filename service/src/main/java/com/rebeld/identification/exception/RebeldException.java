package com.rebeld.identification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="I/O File error")
public class RebeldException extends RuntimeException {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = -509039051060030063L;

	private String exceptionMsg;

	public RebeldException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	public String getExceptionMsg(){
		return this.exceptionMsg;
	}
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
}
