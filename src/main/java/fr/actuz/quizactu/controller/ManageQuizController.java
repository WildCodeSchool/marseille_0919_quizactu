package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class ManageQuizController {

	@Autowired
	private QuizService service;

	@GetMapping("/public/listQuestion/{id}")
	public String getQuestions(Model model, @PathVariable Integer id) {
		model.addAttribute("quiz", this.service.read(id));
		return "public/listQuestion";

	}

}
