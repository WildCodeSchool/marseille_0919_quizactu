package fr.actuz.quizactu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
			return "redirect:/homePage";
		}

	}
	
	@PostMapping("/changedPassword")
//	@PreAuthorize("hasRole('READ_PRIVILEGE')")
//	@ResponseBody
	public String update(@Valid Account account, BindingResult result, String newPassword) {
		
		if (newPassword != account.getPassword()) {
			account.setPassword(newPassword);
			account.setEmail("truc@gmail.com");
			account.setUserName("truc");
			final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			final String hashedPassword = passwordEncoder.encode(newPassword);
//			account.setPassword(hashedPassword);
			service.update(new Account(account.getUsername(), account.getEmail(), hashedPassword));
//			service.update(account);
//		//	service.update(account);
//			return "redirect:/";
			
		}else {
			return "redirect:/ranking";
		}
		
		 return "redirect:/";
	}
	
	
	
	
	@GetMapping("/public/forgotPassword")
	public String forgot() {
		return "public/forgotPassword";
	}
}
