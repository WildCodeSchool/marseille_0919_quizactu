package fr.actuz.quizactu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.actuz.quizactu.business.entity.Account;

@Controller
public class IndexController {
	@GetMapping("/")
	public String user() {
		return "index";
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public String username(@PathVariable int id) {
		switch (id) {
		case 1: 
			return "cas 1";
		case 2:
			return "cas 2";
		case 3: 
			return "cas 3";
		case 4:
			return "cas 4";
		case 5: 
			return "cas 5";
		case 6:
			return "cas 6";
		}
		return "index";
	}
	
	@GetMapping("/connection")
	public String connection(Model model) {
		model.addAttribute("userForm", new Account());
		return "login";
	}
	
}
