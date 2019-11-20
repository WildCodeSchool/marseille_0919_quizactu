package fr.actuz.quizactu.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.QuizRecord;
import fr.actuz.quizactu.business.service.AccountService;
import fr.actuz.quizactu.business.service.ArticleService;
import fr.actuz.quizactu.business.service.QuizRecordService;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
@SessionAttributes({ "accountId", "quiz", "questionIndex" })
@Scope("session")
public class QuizController {

	@Autowired
	private QuizService service;

	@Autowired
	private AccountService accountService;

	@Autowired
	private QuizRecordService recordService;

	@Autowired
	private ArticleService articleService;

	@ModelAttribute("accountId")
	public Integer account() {
		return null;
	}

	@ModelAttribute("quiz")
	public Quiz quiz() {
		return null;
	}

	@ModelAttribute("questionIndex")
	public Integer question() {
		return null;
	}

	@GetMapping("/quizNotFound")
	public String quizNotFound(Model model) {
		model.addAttribute("today", this.service.getTodayQuiz());
		model.addAttribute("yesterday", this.service.getYesterdayQuiz());
		model.addAttribute("dayBeforeYesterday", this.service.getDayBeforeYesterdayQuiz());
		return "quizNotFound";
	}
	
	@GetMapping("/quizDone")
	public String quizDone(Model model) {
		model.addAttribute("yesterday", this.service.getYesterdayQuiz());
		model.addAttribute("dayBeforeYesterday", this.service.getDayBeforeYesterdayQuiz());
		return "quizDone";
	}

	@GetMapping("/quiz/{type}")
	public String vuQuestion(Model model, @ModelAttribute("accountId") Integer accountId, Principal principal,
			@PathVariable String type) {
		Quiz quiz = null;
		if (type.equals("today")) {
			quiz = this.service.getTodayQuiz();
		} else if (type.equals("yesterday")) {
			quiz = this.service.getYesterdayQuiz();
		} else if (type.equals("dayBeforeYesterday")) {
			quiz = this.service.getDayBeforeYesterdayQuiz();
		}

		if (quiz != null) {
			int index = 0;

			model.addAttribute("quiz", quiz);		
			model.addAttribute("question", quiz.getQuestions().get(index));
			model.addAttribute("questionIndex", index);
			model.addAttribute("validation", false);
			if (accountId == null) {
				Account account = this.accountService.read(principal.getName());
				model.addAttribute("accountId", account.getId());
			}

			List<QuizRecord> records = this.recordService.getByQuizIdAndAccountId(quiz.getId(), accountId);
			if (records.isEmpty() && type.equals("today")) {
				return "quiz";
			} else if (type.equals("yesterday")) {
				return "quiz";
			} else if (type.equals("dayBeforeYesterday")) {
				return "quiz";
			} else {
				return "redirect:/quizDone";
		    }

		} else {
			return "redirect:/quizNotFound";
		}
	}

	@GetMapping("nextQuestion")
	public String nextQuestion(Model model, @ModelAttribute("quiz") Quiz quiz,
			@ModelAttribute("questionIndex") int index) {
		// Passe à la question suivante tant qu'il reste des questions, sinon passe à
		// aux resultats.
		if (index < quiz.getQuestions().size() - 1) {
			model.addAttribute("question", quiz.getQuestions().get(++index));
			model.addAttribute("questionIndex", index);
			model.addAttribute("validation", false);
			return "quiz";
		} else {
			return "redirect:/result";
		}
	}

	@GetMapping("validateQuestion/{questionId}/{responseId}")
	public String validateQuestion(Model model, @PathVariable Integer questionId, @PathVariable Integer responseId,
			@ModelAttribute("quiz") Quiz quiz, @ModelAttribute("questionIndex") int index,
			@ModelAttribute("accountId") Integer accountId) {
		// Vérifie si l'utilisateur n'a pas déjà répondu à la question avant de lui
		// donner des points
		if (this.recordService.compareIfQuestionAlreadyAnswered(
				quiz.getId(), accountId, questionId)) {
			this.service.getPoints(accountId, responseId);
			this.recordService.recordResultQuiz(quiz.getId(), questionId,
					responseId, accountId);
		}
        //else if(recordService.compareIfQuestionAlreadyAnswered(quiz.getId(), accountId, responseId, quiz.getQuestions().get(index))) {
		//	QuizRecord newRecord = new QuizRecord(quiz, this.service.getResponseById(responseId), this.accountService.getById(accountId));
			//MARCHE PAS, IL FAUT QUE CA UPDATE QUAND Y'A UN NOUVEAU
		//	this.recordService.updateResultQuiz(newRecord);
		//}
		model.addAttribute("validation", true);
		model.addAttribute("question", quiz.getQuestions().get(index));
		return "quiz";
	}

	@GetMapping("validateQuestion/{questionId}")
	public String validateQuestionWithoutResponse(Model model,
			@PathVariable Integer questionId,
			@ModelAttribute("quiz") Quiz quiz,
			@ModelAttribute("questionIndex") int index,
			@ModelAttribute("accountId") Integer accountId) {
		} 
		this.recordService.recordResultQuiz(quiz.getId(), questionId, null,
				accountId);
		model.addAttribute("validation", true);
		model.addAttribute("question", quiz.getQuestions().get(index));
		return "quiz";
	}

	@GetMapping("/result")
	public String getResult(Model model, @ModelAttribute("accountId") Integer accountId,
			@ModelAttribute("quiz") Quiz quiz) {
		Account account = this.accountService.getById(accountId);
		model.addAttribute("totalScore", account.getScore());
		model.addAttribute("scoreOfQuiz", this.recordService.getScoreQuiz(quiz.getId(), accountId));
		model.addAttribute("listResponse", this.recordService.getQuizResponses(quiz.getId(), accountId));
		model.addAttribute("articles", account.getArticles());
		return "result";
	}

	@GetMapping("/timer/{type}")
	public String timer(@PathVariable String type, Model model) {
		model.addAttribute("type", type);
		return "timer";
	}

	@GetMapping("/favArticle/{articleId}")
	@ResponseBody
	public boolean favArticle(@ModelAttribute("accountId") Integer accountId, @PathVariable Integer articleId) {
		return this.articleService.favoriteArticle(accountId, articleId);
	}

	@GetMapping("/public/homeManager")
	public String listQuizCreate(Model model) {
		model.addAttribute("listQuiz", this.service.getAll());
		return "public/homeManager";
	}

	@GetMapping("/public/createQuiz")
	public String createQuiz() {
		return "public/createQuiz";
	}
}