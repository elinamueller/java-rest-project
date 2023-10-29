package com.project.RestAPI2.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.RestAPI2.entity.Payment;

public interface PaymentService {
	
	Page<Payment> getAllPayments(Pageable page);
	
	List<Payment> getAllPaymentsByName(String keyword, Pageable page);
	
	List<Payment> getAllPaymentsByMethod(String method, Pageable page);
	
	List<Payment> getAllPaymentsByStatus(String status, Pageable page);
	
	List<Payment> getAllPaymentsByDateBetween(Date startDate, Date endDate, Pageable page);
	
	List<Payment> getAllPaymentsByAmountBetween(BigDecimal startAmount, BigDecimal endAmount, Pageable page);
	
	Payment getPaymentById(Long id);
	
	void deletePaymentById(Long id);
	
	Payment addPaymentDetails(Payment payment);
	
	Payment updatePaymentDetails(Long id, Payment payment);
	
	//----------------test----------------
	List<Payment> getAllPaymentsTest();

}
