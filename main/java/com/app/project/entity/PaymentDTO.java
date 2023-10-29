package com.app.project.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class PaymentDTO implements Serializable {

	private Long id;
	private String name;
	private String method;
	private BigDecimal amount;
	private Boolean status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate date;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String username;
	
	public PaymentDTO(String name, String method, BigDecimal amount, Boolean status, LocalDate date) {
		super();
		this.name = name;
		this.method = method;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}

}
