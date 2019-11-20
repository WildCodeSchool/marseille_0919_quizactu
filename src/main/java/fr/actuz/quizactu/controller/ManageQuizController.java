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
	
	@GetMapping("/public/listResponses/{questionId}")
	public String listResp(@PathVariable int questionId, Model model) {
		model.addAttribute("listResp", this.service.getAllResp(questionId)); 
		return "public/listResponses";
	}
}
