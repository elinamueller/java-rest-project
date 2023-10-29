package com.project.RestAPI2.runner;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.RestAPI2.entity.Payment;
import com.project.RestAPI2.service.PaymentService;

//@Component
public class PaymentRunner implements CommandLineRunner {
	
	@Autowired
	PaymentService service;

	@Override
	public void run(String... args) throws Exception {
		service.addPaymentDetails(new Payment("Machine Learning Course","PayPal",BigDecimal.valueOf(89.99),true,
												LocalDate.now()));
		service.addPaymentDetails(new Payment("Biology Book","Invoice",BigDecimal.valueOf(59.99),false,
												LocalDate.now()));
		service.addPaymentDetails(new Payment("Chemistry Course","PayPal",BigDecimal.valueOf(119.99),true,
												LocalDate.now()));
		service.addPaymentDetails(new Payment("Astrobiology Book","Cash",BigDecimal.valueOf(69.00),true,
												LocalDate.now()));
	}

}
