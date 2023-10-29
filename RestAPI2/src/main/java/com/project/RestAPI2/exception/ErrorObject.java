package com.project.RestAPI2.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorObject {
	
	private String message;
	private Integer statusCode;
	private Date timestamp;

}
