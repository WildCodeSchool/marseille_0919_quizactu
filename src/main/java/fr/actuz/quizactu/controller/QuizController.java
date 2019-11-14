package fr.actuz.quizactu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.service.AccountService;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
@SessionAttributes({"accountId", "quiz", "questionIndex"})
@Scope("session")
public class QuizController {

	@Autowired
	private QuizService service;
	
	@Autowired
	private AccountService accountService;
	
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
	public String nextQuestion(Model model, Integer questionId, Integer responseId, @ModelAttribute("quiz") Quiz quiz, @ModelAttribute("questionIndex") int index) {
		// TODO: Enregistrer la réponse choisie en BDD.
		if (index < quiz.getQuestions().size() - 1) {
			model.addAttribute("question", quiz.getQuestions().get(++index));
			
			return "quiz";
		} else {
			// Affichage page résultats.
			return "result";
		}
	}

}