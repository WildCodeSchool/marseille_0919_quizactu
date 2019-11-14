package fr.actuz.quizactu.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.service.AccountService;

@Controller


public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping("/changedPassword")
	public String change(Model model, HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		Account acc = service.read(user);
		model.addAttribute("connectedId", acc.getId());
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
	
	@PostMapping("/changedPassword")
//	@PreAuthorize("hasRole('READ_PRIVILEGE')")
	public String newPassword(Integer id, String newPassword) {

	//	Logger LOG = LoggerFactory.getLogger("Wilder");
		service.updatePassword(id, newPassword);
		
		return "redirect:/";
	}
	
	
	
	
	@GetMapping("/public/forgotPassword")
	public String forgot() {
		return "public/forgotPassword";
	}
}
