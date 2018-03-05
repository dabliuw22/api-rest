package com.leysoft.app.util;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leysoft.app.exception.NotFoundException;

@RestControllerAdvice
public class RestControllerResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})
	public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(e.getMessage(), new Date(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.toString(), HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {NotFoundException.class})
	public final ResponseEntity<Object> handleNotFoundException(Exception e, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(e.getMessage(), new Date(), request.getDescription(false), HttpStatus.NOT_FOUND.toString(), HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Método re-escrito para cuando se valide y no se cumpla
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(e.getMessage(), new Date(), e.getBindingResult().toString(), HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}