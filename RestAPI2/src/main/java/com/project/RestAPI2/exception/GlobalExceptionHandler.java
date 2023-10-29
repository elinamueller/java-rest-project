package com.project.RestAPI2.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handlePaymentNotFoundException(ResourceNotFoundException exception, 
																		WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(exception.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException exception,
																				WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorObject.setMessage(exception.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception exception, WebRequest request) {
		ErrorObject errorObject = new ErrorObject();
		errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorObject.setMessage(exception.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, 
																	HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("timestamp", new Date());
		body.put("statusCode", HttpStatus.BAD_REQUEST.value());
		List<String> errors = exception.getBindingResult()
										.getFieldErrors()
										.stream()
										.map(x -> x.getDefaultMessage())
										.collect(Collectors.toList());
		body.put("messages", errors);
		return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
	}

}
