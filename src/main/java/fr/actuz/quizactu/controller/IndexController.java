package fr.actuz.quizactu.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
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

		
		Quiz todayQuiz = this.quizServ.getTodayQuiz();
		if (todayQuiz != null && todayQuiz.getQuestions().size() > 0) {
			Question question = todayQuiz.getQuestions().get(0);
			if (question.getImageEncoded() != null) {
				model.addAttribute("firstPictureOfQuizOfTheDay", question.getImageEncoded());
			}
		}

		Quiz yesterdayQuiz = this.quizServ.getYesterdayQuiz();
		if (yesterdayQuiz != null && yesterdayQuiz.getQuestions().size() > 0) {
			Question question = yesterdayQuiz.getQuestions().get(0);
			if (question.getImageEncoded() != null) {
				model.addAttribute("firstPictureOfQuizYesterday", question.getImageEncoded());
			}
		}
			
		Quiz beforeYesterdayQuiz = this.quizServ.getDayBeforeYesterdayQuiz();
		if (beforeYesterdayQuiz != null && beforeYesterdayQuiz.getQuestions().size() > 0) {
			Question question = beforeYesterdayQuiz.getQuestions().get(0);
			if (question.getImageEncoded() != null) {
				model.addAttribute("firstPictureOfQuizBeforeYesterday", question.getImageEncoded());
			}
		}


		return "homePage";
	}

	@GetMapping("/ranking")
	public String ranking(Model model) {
		System.out.println(this.accountServ.getScoreOnRankingPage());
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
