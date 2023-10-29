package com.project.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_expenses")
public class Expense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "expense_name")
	private String name;
	private String description;
	
	@Column(name = "expense_amount")
	private BigDecimal amount;
	private String category;
	private Date date;
	
	public Expense(String name, String description, BigDecimal amount, String category, Date date) {
		super();
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.category = category;
		this.date = date;
	}

}
