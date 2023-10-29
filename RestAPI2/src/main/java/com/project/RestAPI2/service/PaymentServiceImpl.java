package com.project.RestAPI2.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.RestAPI2.entity.Payment;
import com.project.RestAPI2.exception.ResourceNotFoundException;
import com.project.RestAPI2.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public Page<Payment> getAllPayments(Pageable page) {
		return paymentRepo.findAll(page);
	}
	
	//----------------test----------------
	public List<Payment> getAllPaymentsTest() {
		return paymentRepo.findAll();
	}

	@Override
	@Cacheable(value = "paymentCache", key = "#id")
	public Payment getPaymentById(Long id) {
		Optional<Payment> payment = paymentRepo.findById(id);
		if (payment.isPresent()) {
			return payment.get();
		}
		throw new ResourceNotFoundException(
				"Payment with id "+ id + "does not exist.");
	}

	@Override
	public Payment addPaymentDetails(Payment payment) {	
	     return paymentRepo.save(payment);
	}

	@Override
	@CachePut(value = "paymentCache", key = "#id")
	@Transactional
	public Payment updatePaymentDetails(Long id, Payment payment) {
		Payment existPayment = paymentRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Payment with "+ id +" does not exist."));
		
		existPayment.setName(payment.getName() != null ? payment.getName() :
																existPayment.getName());
		existPayment.setMethod(payment.getMethod() != null ? payment.getMethod() :
																existPayment.getMethod());
		existPayment.setStatus(payment.getStatus() != null ? payment.getStatus() :
																existPayment.getStatus());
		existPayment.setAmount(payment.getAmount() != null ? payment.getAmount() :
																existPayment.getAmount());
		existPayment.setDate(payment.getDate() != null ? payment.getDate() :
																existPayment.getDate());
		return paymentRepo.save(existPayment);
	}
	
	@Override
	public void deletePaymentById(Long id) {
		Payment payment = paymentRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Payment with "+ id +" does not exist."));
		paymentRepo.delete(payment);
	}

	@Override
	public List<Payment> getAllPaymentsByName(String keyword, Pageable page) {
		return paymentRepo.findByName(keyword, page).toList();
	}

	@Override
	public List<Payment> getAllPaymentsByMethod(String method, Pageable page) {
		return paymentRepo.findByMethod(method, page).toList();
	}

	@Override
	public List<Payment> getAllPaymentsByStatus(String status, Pageable page) {
		return paymentRepo.findByStatus(status, page).toList();
	}

	@Override
	public List<Payment> getAllPaymentsByDateBetween(Date startDate, Date endDate, Pageable page) {
		if (startDate == null) {
			startDate = new Date(0);
		}
		if (endDate == null) {
			endDate = new Date(System.currentTimeMillis());
		}
		return paymentRepo.findByDateBetween(startDate, endDate, page).toList();
	}

	@Override
	public List<Payment> getAllPaymentsByAmountBetween(BigDecimal startAmount, BigDecimal endAmount, Pageable page) {
		return paymentRepo.findByAmountBetween(startAmount, endAmount, page).toList();
	}

}
