package com.project.RestAPI2.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.RestAPI2.entity.Payment;
import com.project.RestAPI2.service.PaymentService;

@RestController
@RequestMapping("/accounts/user")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;

	@GetMapping("/payments")
	public ResponseEntity<List<Payment>> getAllPayments(Pageable page) {
		List<Payment> paymentList = paymentService.getAllPayments(page).toList();	
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}

	@GetMapping("/payments/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
		return new ResponseEntity<Payment>(paymentService.getPaymentById(id), HttpStatus.FOUND);
	}

	@PostMapping("/payments")
	public ResponseEntity<Payment> addPaymentDetails(@Valid @RequestBody Payment payment) {
		return new ResponseEntity<Payment>(paymentService.addPaymentDetails(payment), HttpStatus.CREATED);
	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<Payment> updatePaymentDetails(@RequestBody Payment payment, @PathVariable Long id) {
		return new ResponseEntity<Payment>(paymentService.updatePaymentDetails(id, payment), HttpStatus.OK);
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity<Void> deletePaymentById(@PathVariable Long id) {
		System.out.println("request is coming in controller");
		paymentService.deletePaymentById(id);
		return new ResponseEntity<Void> (HttpStatus.NO_CONTENT);
	}

	@GetMapping("/payments/name")
	public ResponseEntity<List<Payment>> getAllPaymentsByName(@RequestParam String keyword, Pageable page) {
		List<Payment> paymentList = paymentService.getAllPaymentsByName(keyword, page);
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}
	
	@GetMapping("/payments/method")
	public ResponseEntity<List<Payment>> getAllPaymentsByMethod(@RequestParam String method, Pageable page) {
		List<Payment> paymentList = paymentService.getAllPaymentsByMethod(method, page);
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}
	
	@GetMapping("/payments/status")
	public ResponseEntity<List<Payment>> getAllPaymentsByStatus(@RequestParam String status, Pageable page) {
		List<Payment> paymentList = paymentService.getAllPaymentsByStatus(status, page);
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}
	
	@GetMapping("/payments/date")
	public ResponseEntity<List<Payment>> getAllPaymentsByDate(@RequestParam(required = false) Date startDate,
											@RequestParam(required = false) Date endDate,
											Pageable page) {
		List<Payment> paymentList = paymentService.getAllPaymentsByDateBetween(startDate, endDate, page);
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}
	
	@GetMapping("/payments/amount")
	public ResponseEntity<List<Payment>> getAllPaymentsByAmount(@RequestParam(required = false) BigDecimal startAmount,
											@RequestParam(required = false) BigDecimal endAmount,
											Pageable page) {
		List<Payment> paymentList = paymentService.getAllPaymentsByAmountBetween(startAmount, endAmount, page);
		return new ResponseEntity<List<Payment>>(paymentList, HttpStatus.FOUND);
	}

}
