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
import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class IndexController {

	@Autowired
	private AccountService accountServ;

	@Autowired
	private QuizService quizServ;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("users", this.accountServ.getScoreLimitTen());

		model.addAttribute("quizToday", LocalDate.now().atStartOfDay().atZone(ZoneId.of("UTC")));

		model.addAttribute("quizOfTheDay", this.quizServ.getTodayQuiz());
		model.addAttribute("quizOfYesterday", this.quizServ.getYesterdayQuiz());
		model.addAttribute("quizOfBeforeYesterday", this.quizServ.getDayBeforeYesterdayQuiz());

		try {
			model.addAttribute("firstPictureOfQuizOfTheDay",
					this.quizServ.getTodayQuiz().getQuestions().get(0).getImageEncoded());
		} catch (NullPointerException e) {
			model.addAttribute("defaultPicture", "Impossible d afficher l image");
		}

		try {
			model.addAttribute("firstPictureOfQuizYesterday",
					this.quizServ.getYesterdayQuiz().getQuestions().get(0).getImageEncoded());
		} catch (NullPointerException e) {
			model.addAttribute("defaultPictureTwo", "Impossible d afficher l image");
		}

		try {
			model.addAttribute("firstPictureOfQuizBeforeYesterday",
					this.quizServ.getDayBeforeYesterdayQuiz().getQuestions().get(0).getImageEncoded());
		} catch (NullPointerException e) {
			model.addAttribute("defaultPictureThree", "Impossible d afficher l image");
		}

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
	
	@GetMapping("/public/policy")
	public String policy() {
		return "public/policy";
	}

}
