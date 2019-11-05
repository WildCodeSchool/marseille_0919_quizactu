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
	
}
