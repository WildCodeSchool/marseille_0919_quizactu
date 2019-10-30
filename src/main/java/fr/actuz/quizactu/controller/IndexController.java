package fr.actuz.quizactu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.actuz.quizactu.business.entity.Account;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/connection")
	public String connection(Model model) {
		model.addAttribute("userForm", new Account());
		return "login";
	}
}
