package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class QuizController {

	@Autowired
	private QuizService service;
	
	@GetMapping("/quiz")
	public String vuQuestion(Model model) {
		model.addAttribute("quizVar", this.service.getQuizById(1));
		return "quiz";
	}
	
}
