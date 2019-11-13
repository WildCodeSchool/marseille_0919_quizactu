package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.service.AccountService;

@Controller
public class IndexController {
	
	@Autowired
	private AccountService accountServ;
	
	@GetMapping("/")
	public String user(Model model) {
		model.addAttribute("users", this.accountServ.getScoreLimitTen());
		return "homePage";
	}

	@GetMapping("/ranking")
	public String ranking(Model model) {
		model.addAttribute("usersScore", this.accountServ.getScoreOnRankingPage());
		return "ranking";
	}
	

	
}
