package com.app.project.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	
	private String message;
	private Integer statusCode;
	private Date timestamp;

}
