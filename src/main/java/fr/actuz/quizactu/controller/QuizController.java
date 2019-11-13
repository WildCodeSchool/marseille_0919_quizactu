package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class QuizController {

	@Autowired
	private QuizService service;

	private Quiz quiz;

	private Integer index;

	private Integer score;

	@GetMapping("/quiz")
	public String vuQuestion(Model model) {
		this.quiz = this.service.getQuizByPublicationDate();
		this.index = 0;
		this.score = 0;
		model.addAttribute("question", this.quiz.getQuestions().get(index));
		model.addAttribute("score", this.score);
		return "quiz";
	}

	@GetMapping("nextQuestion/{questionId}/{responseId}")
	public String nextQuestion(Model model, @PathVariable Integer questionId, @PathVariable Integer responseId) {
		Response resp = service.getResponseById(responseId);
		//Si la réponse choisie est juste, incrémente le score de 10
		if (resp.getIsTrue()) {
			this.score += 10;
		}
		model.addAttribute("score", this.score);
		if (index < quiz.getQuestions().size() - 1) {
			model.addAttribute("question", this.quiz.getQuestions().get(++index));
			return "quiz";
		} else {
			// Redirige vers page résultats.
			return "redirect:/result";
		}
	}
	
	@GetMapping("/result")
	public String getResult(Model model) {
		model.addAttribute("score", this.score);
		return "result";
	}

}