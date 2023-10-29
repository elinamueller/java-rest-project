package com.project.RestAPI2.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="payment_name")
	@NotBlank(message="Payment name should not be empty.")
	@Size(min = 3, message="Payment name must contain at least three characters.")
	private String name;
	
	@NotBlank(message="Payment method should not be empty.")
	private String method;
	
	@Column(name="payment_amount")
	@NotNull(message="Payment amount should not be zero.")
	private BigDecimal amount;
	
	@Column(name="payment_status")
	@NotNull(message="Payment status should not be empty.")
	private Boolean status;
	
	@NotNull(message="Payment date should not be empty.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@Column(name="created_at", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	public Payment(String name, String method, BigDecimal amount, Boolean status, LocalDate date) {
		super();
		this.name = name;
		this.method = method;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}

	public Payment(Long id, String name, String method, BigDecimal amount, Boolean status, LocalDate date) {
		super();
		this.id = id;
		this.name = name;
		this.method = method;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}

}
