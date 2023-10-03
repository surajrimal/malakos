package com.backend.tbdwm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.tbdwm.DTO.*;

@RestControllerAdvice
public class ExceptionControlAdvice {
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(NoSuchWordException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(NoSuchWordException ex){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

}
