package com.app.project.service;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.project.entity.Course;
import com.app.project.entity.PaymentDTO;
import com.app.project.repository.CourseRepository;

@Service
public class PaymentDTOService {
	
	private static final String GET_PAYMENTS_ENDPOINT_URL = "http://localhost:9090/accounts/user/payments";
	private static final String GET_PAYMENT_ENDPOINT_URL = "http://localhost:9090/accounts/user/payments/{id}";
	private static final String ADD_PAYMENT_ENDPOINT_URL = "http://localhost:9090/accounts/user/payments";
    private static final String UPDATE_PAYMENT_ENDPOINT_URL = "http://localhost:9090/accounts/user/payments/";
    private static final String DELETE_PAYMENT_ENDPOINT_URL = "http://localhost:9090/accounts/user/payments/";
    
	private RestTemplate restTemplate = new RestTemplate();
	
	public List<PaymentDTO> getAllPayments(String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<PaymentDTO[]> result = restTemplate.getForEntity(GET_PAYMENTS_ENDPOINT_URL, PaymentDTO[].class);
		List<PaymentDTO> paymentList = new ArrayList<PaymentDTO>(Arrays.asList(result.getBody()));	
		
		if (result.getStatusCode().is2xxSuccessful()) { 
			for(PaymentDTO payment : paymentList ) {
				if(payment.getUsername().equals(username))
					paymentList = new ArrayList<PaymentDTO>(Arrays.asList(result.getBody()));
				}
			}				
		return paymentList;
	}
	 
	public PaymentDTO getPaymentById(Long paymentId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		PaymentDTO payment = new PaymentDTO();
		ResponseEntity<PaymentDTO> result = restTemplate.exchange(GET_PAYMENT_ENDPOINT_URL, HttpMethod.GET, entity, PaymentDTO.class, paymentId);
		PaymentDTO dto = result.getBody();
		return dto;
}

	public void addPayment(PaymentDTO payment) {	
		String resourceUrl = ADD_PAYMENT_ENDPOINT_URL; 
    	RestTemplate restTemplate = new RestTemplate(); 
    	PaymentDTO newPayment = new PaymentDTO(); 
    	newPayment.setName(payment.getName()); 
    	newPayment.setMethod(payment.getMethod());
    	newPayment.setAmount(payment.getAmount());
    	newPayment.setStatus(payment.getStatus());
    	newPayment.setDate(payment.getDate());
    	URI location = restTemplate.postForLocation(resourceUrl, newPayment); 
    } 
 
	public void  updatePaymentById(Long paymentId, PaymentDTO payment) {
    	String resourceUrl = UPDATE_PAYMENT_ENDPOINT_URL; 
    	RestTemplate restTemplate = new RestTemplate(); 
    	restTemplate.put(resourceUrl + paymentId, payment); 
    }

	public void deletePaymentById(Long paymentId) {
        Map <Long, String> params = new HashMap <Long,String>();
        restTemplate.delete(DELETE_PAYMENT_ENDPOINT_URL + paymentId);
    }
		
}
