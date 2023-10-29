package com.project.RestAPI2.test.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

import org.assertj.core.api.Assert;
import org.assertj.core.api.IntPredicateAssert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.RestAPI2.entity.Payment;
import com.project.RestAPI2.repository.PaymentRepository;
import com.project.RestAPI2.service.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentTest {
	
	@Autowired
	PaymentService paymentService;
	
	@MockBean
	PaymentRepository paymentRepo;
	
	@Test
	public void getAllPayments() {		
		Mockito.when(paymentRepo.findAll())
				.thenReturn(Arrays.asList(new Payment("Machine Learning Course","PayPal",BigDecimal.valueOf(89.99),true,LocalDate.now()),
											new Payment("Biology Book","Invoice",BigDecimal.valueOf(59.99),false,LocalDate.now()),
											new Payment("Chemistry Course","PayPal",BigDecimal.valueOf(119.99),true,LocalDate.now()),
											new Payment("Astrobiology Book","Cash",BigDecimal.valueOf(69.00),true,LocalDate.now())));
		List<Payment> paymentList = paymentService.getAllPaymentsTest();
		assertEquals("Machine Learning Course", paymentList.get(0).getName());
		assertEquals("Invoice", paymentList.get(1).getMethod());
		assertEquals(true, paymentList.get(2).getStatus());
		assertEquals(BigDecimal.valueOf(69.00), paymentList.get(3).getAmount());
	}
	
	@Test
	public void addPaymentDetails() {
		Payment newPayment = new Payment("Java Course","PayPal",BigDecimal.valueOf(129.99),true,LocalDate.now());
		Mockito.when(paymentRepo.save(newPayment)).thenReturn(newPayment);
		
		Payment payment2 = paymentService.addPaymentDetails(newPayment);		
		assertEquals(BigDecimal.valueOf(129.99), payment2.getAmount());
		assertEquals("PayPal", payment2.getMethod());
		assertEquals("Java Course", payment2.getName());
		
////		//when
//		paymentRepo.save(newPayment);
////		//then
//		ArgumentCaptor<Payment> paymentArgumentCaptor = ArgumentCaptor.forClass(Payment.class);
//		verify(paymentService.addPaymentDetails(paymentArgumentCaptor.capture()));
//		Payment capturedPayment = paymentArgumentCaptor.getValue();
//		assertThat(capturedPayment.getName()).isEqualTo(newPayment.getName());
	}
	
//	@Test
//	public void updatePaymentDetails() {
//		Long id = 1L;
//		Payment newPayment = new Payment(id,"Java Course","PayPal",BigDecimal.valueOf(129.99),true,new java.sql.Date(System.currentTimeMillis()));
//		Payment updatedPayment = new Payment(id,"Scala Course","PayPal",BigDecimal.valueOf(165.00),true,new java.sql.Date(System.currentTimeMillis()));
//		//when
//		paymentService.updatePaymentDetails(id, newPayment);
//		//then
//		verify(paymentRepo.save(updatedPayment));
//		assertThat(updatedPayment.getName()).isEqualTo(newPayment.getName());
//	}
	
	@Test
	public void deletePaymentById() {
		Long id = 1L;
		Mockito.when(paymentRepo.existsById(id)).thenReturn(true);
		//when
		paymentService.deletePaymentById(id);
		//then
		verify(paymentRepo).deleteById(id);		
	}

}
