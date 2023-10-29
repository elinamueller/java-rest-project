package com.project.RestAPI2.repository;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.RestAPI2.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	//SELECT * FROM payments WHERE name LIKE '%name%'
	@Query(value="SELECT * FROM expensetracker.payments WHERE payments.payment_name LIKE '%:name%'", nativeQuery = true)
	Page<Payment> findByName(@Param(value = "name") String name, Pageable page);
	
	//SELECT * FROM payments WHERE method LIKE '%method%'
	Page<Payment> findByMethod(String method, Pageable page);
	
	//SELECT * FROM payments WHERE status LIKE '%status%'
	Page<Payment> findByStatus(String status, Pageable page);
	
	//SELECT * FROM payments WHERE date BETWEEN 'startDate' AND 'endDate'
	Page<Payment> findByDateBetween(Date startDate, Date endDate, Pageable page);
	
	//SELECT * FROM payments WHERE amount BETWEEN 'startAmount' AND 'endAmount'
	Page<Payment> findByAmountBetween(BigDecimal startAmount, BigDecimal endAmount, Pageable page);
}
