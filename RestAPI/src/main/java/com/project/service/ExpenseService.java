package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entity.Expense;

//@Service
public interface ExpenseService {

	void save(Expense expense);
	
	List<Expense> getAllExpenses();

}
