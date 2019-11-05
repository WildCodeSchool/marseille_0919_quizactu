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
	
//	@GetMapping("/createAccount")
//	public String create(Model model) {
//		model.addAttribute("account", new Account());
//		return "public/createAccount";
//	}
	
	@GetMapping("/public/createAccount")
	public String create() {
		return "public/createAccount";
	}
	
	@PostMapping("/public/form")
	public String save(@Valid Account account, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result.toString());
			return "public/createAccount";
		}else {
			if (account.getId() != null) {
				return "public/createAccount";
			}else {
				service.create(account.getUsername(), account.getEmail(), account.getPassword());
				System.out.println("creation ok");
			}
			return "redirect:/index";
		}
		
	}

}
