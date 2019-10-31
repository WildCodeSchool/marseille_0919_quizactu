package fr.actuz.quizactu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
	
	
	@GetMapping("/login")
	public String login() {
		return "login";	
	}
//	@GetMapping("/createAccount")
//	public String create() {
//		return "createAccount";
//	}


}
