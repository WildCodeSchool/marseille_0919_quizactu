package fr.actuz.quizactu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class AuthController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/disconnected")
	public String disconnected(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
