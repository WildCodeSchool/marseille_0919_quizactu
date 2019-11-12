package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class QuizController {

	@Autowired
	private QuizService service;

	private Quiz quiz;

	private Integer index;

	@GetMapping("/quiz")
	public String vuQuestion(Model model) {
		this.quiz = this.service.getQuizByPublicationDate();
		this.index = 0;
		model.addAttribute("question", this.quiz.getQuestions().get(index));
		return "quiz";
	}

	@GetMapping("nextQuestion/{questionId}/{responseId}")
	public String nextQuestion(Model model, Integer questionId, Integer responseId) {
		// TODO: Enregistrer la réponse choisie en BDD.
		if (index < quiz.getQuestions().size() - 1) {
			model.addAttribute("question", this.quiz.getQuestions().get(++index));
			
			return "quiz";
		} else {
			// Affichage page résultats.
			return "result";
		}
	}

}