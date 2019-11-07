package fr.actuz.quizactu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/changedPassword")
	public String change() {
		return "changedPassword";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("/public/createAccount")
	public String create() {
		return "public/createAccount";
	}
	
	@PostMapping("/public/form")
	public String save(@Valid Account account, BindingResult result, String confirmPassword) {
		if(result.hasErrors()) {
			return "public/createAccount";
		}else {
			if (confirmPassword.equals(account.getPassword())) {
				service.create(account.getUsername(), account.getEmail(), account.getPassword());
			}else {
				return "public/createAccount";			
			}
			return "redirect:/";
		}

	}

	
	@GetMapping("/public/forgotPassword")
	public String forgot() {
		return "public/forgotPassword";
	}
	
}
