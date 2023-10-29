package com.app.project.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.project.entity.Course;
import com.app.project.entity.PaymentDTO;
import com.app.project.service.PaymentDTOService;

@RestController
@RequestMapping("/accounts/user")
@SessionAttributes("username")
public class PaymentController {
	
	@Autowired
	PaymentDTOService paymentDTOService;
	
	@GetMapping("/payments")
	public ModelAndView listAllPayments(ModelMap model, Principal principal) {
		String username = principal.getName();
		model.put("username", username); 
		List<PaymentDTO> paymentList = (List<PaymentDTO>) paymentDTOService.getAllPayments(username);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/list_payments");
		modelAndView.addObject("paymentList", paymentList);
		return modelAndView;
	}

	@GetMapping("/payments/add")
	public ModelAndView showNewPaymentPage(ModelMap model, PaymentDTO payment, Principal principal) {
		String username = (String)model.get("username");
		model.put("username", username); 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/add_payment");
		return modelAndView;
	}
	
	@PostMapping("/payments/add")
	public ModelAndView addNewPayment(@Valid @ModelAttribute("payment") PaymentDTO payment, 
										BindingResult result, ModelMap model, Principal principal) {
		if(result.hasErrors()) {
			ModelAndView modelAndView1 = new ModelAndView("redirect:/accounts/user/payments/add");
			return modelAndView1;
		}
		payment.setUsername(principal.getName());
		paymentDTOService.addPayment(payment);
		ModelAndView modelAndView2 = new ModelAndView("redirect:/accounts/user/payments");
		modelAndView2.addObject("payment", payment);
		return modelAndView2;
	}
	
	@GetMapping("/payments/update")
	public ModelAndView updatePayment(@RequestParam Long id, ModelMap model, 
										PaymentDTO payment, Principal principal) {
		String username = (String)model.get("username");
		model.put("username", username); 
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/update_payment");
		PaymentDTO exsistingPayement = paymentDTOService.getPaymentById(id);
		modelAndView.addObject("paymentDTO", exsistingPayement);
		return modelAndView;
	}	

	@PostMapping("/payments/update")
	public ModelAndView updatePayment(@ModelAttribute("payment") PaymentDTO payment, 
										BindingResult result, Principal principal) {
		paymentDTOService.updatePaymentById(payment.getId(), payment);
		ModelAndView modelAndView1 = new ModelAndView("redirect:/accounts/user/payments");
		return modelAndView1;
	}
	
	@GetMapping("/payments/delete")
	public ModelAndView deletePayment(@RequestParam Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/accounts/user/payments");
		paymentDTOService.deletePaymentById(id);
		return modelAndView;
	}

}
