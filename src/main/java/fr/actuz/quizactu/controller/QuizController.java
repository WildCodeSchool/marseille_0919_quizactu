package fr.actuz.quizactu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.service.AccountService;
import fr.actuz.quizactu.business.service.QuizRecordService;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
@SessionAttributes({"accountId", "quiz", "questionIndex"})
@Scope("session")
public class QuizController {

	@Autowired
	private QuizService service;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private QuizRecordService recordService;
	
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

	@GetMapping("/quiz")
	public String vuQuestion(Model model, 
			@ModelAttribute("accountId") Integer accountId,
			Principal principal) {
		Quiz quiz = this.service.getQuizByPublicationDate();
		int index = 0;
		model.addAttribute("quiz", quiz);
		model.addAttribute("question", quiz.getQuestions().get(index));
		model.addAttribute("questionIndex", index);
		if(accountId == null) {
			Account account = this.accountService.read(principal.getName());
			model.addAttribute("accountId", account.getId());
		}
		return "quiz";
		
	}

	@GetMapping("nextQuestion/{questionId}/{responseId}")
	public String nextQuestion(Model model, @PathVariable Integer questionId, @PathVariable Integer responseId, @ModelAttribute("quiz") Quiz quiz, @ModelAttribute("questionIndex") int index) {
		// TODO: Enregistrer la réponse choisie en BDD.
		// Response resp = service.getResponseById(responseId);
		//Si la réponse choisie est juste, incrémente le score de 10
		// if (resp.getIsTrue()) {
		// 	this.score += 10;
		// }
		if (index < quiz.getQuestions().size() - 1) {
			model.addAttribute("question", quiz.getQuestions().get(++index));
			model.addAttribute("questionIndex", index);
			return "quiz";
		} else {
			// Redirige vers page résultats.
			return "redirect:/result";
		}
	}
	
//	@GetMapping("/result")
//	public String getResult(Model model) {
//		// TODO: Calculer le score du quiz et récupérer le score total.
//		model.addAttribute("score", 5);
//		return "result";
//	}
	
	@GetMapping("/result")
	public String getScoreQuiz(Model model, Integer quizId, Integer responseId, Integer accountId) {
		model.addAttribute("score", this.recordService.recordResultQuiz(quizId, responseId, accountId));
		model.addAttribute("result", this.recordService.getScoreQuiz(quizId, accountId));
		return "result";
	}
	

}