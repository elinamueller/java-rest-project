package com.project.runner;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.entity.Expense;
import com.project.service.ExpenseService;

@Component
public class ExpenseRunner implements CommandLineRunner {

	@Autowired
	ExpenseService service;
	
	@Override
	public void run(String... args) throws Exception {
		service.save(new Expense("Groceries", "Groceries bill Janruary", 
				BigDecimal.valueOf(300.00), "Bills", new java.sql.Date(System.currentTimeMillis())));
		service.save(new Expense("Heating", "Heating bill February", 
				BigDecimal.valueOf(100.00), "Bills", new java.sql.Date(System.currentTimeMillis())));
	}

}
