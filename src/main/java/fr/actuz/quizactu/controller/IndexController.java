package fr.actuz.quizactu.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.service.AccountService;

@Controller
public class IndexController {

	@Autowired
	private AccountService accountServ;

	@GetMapping("/")
	public String user(Model model) {
		model.addAttribute("users", this.accountServ.getScoreLimitTen());
		model.addAttribute("quizToday", LocalDate.now().atStartOfDay().atZone(ZoneId.of("UTC")));
		model.addAttribute("quizYesterday", LocalDate.now().minusDays(1).atStartOfDay().atZone(ZoneId.of("UTC")));
		model.addAttribute("quizBeforeYesterday", LocalDate.now().minusDays(2).atStartOfDay().atZone(ZoneId.of("UTC")));
		return "homePage";
	}

	@GetMapping("/ranking")
	public String ranking(Model model) {
		model.addAttribute("usersScore", this.accountServ.getScoreOnRankingPage());
		return "ranking";
	}

	@GetMapping("/favoriteArticles")
	public String accountProfile(Model model, Integer accountId, Integer articleId, Principal principal) {
		Account account = this.accountServ.read(principal.getName());
		model.addAttribute("account", account);
		return "favoriteArticles";
	}

}
