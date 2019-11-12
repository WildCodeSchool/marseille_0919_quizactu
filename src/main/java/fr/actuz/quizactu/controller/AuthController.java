package fr.actuz.quizactu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping({ "/login", "/signin" })
	public String login() {
		return "login";
	}

}
