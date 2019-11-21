package fr.actuz.quizactu.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.actuz.quizactu.business.entity.Quiz;
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

	@GetMapping("/public/listQuestion/{id}")
	public String getQuestions(Model model, @PathVariable Integer id) {
		model.addAttribute("quiz", this.service.read(id));
		return "public/listQuestion";

	}
	
	@GetMapping("/public/createQuiz")
	public String showFormQuiz() {
		return "public/createQuiz";
	}
	
	@GetMapping("public/modifyQuiz/{quizId}")
	public String showModifyQuiz(@PathVariable Integer quizId, Model model) {
		Quiz quiz = this.service.read(quizId);
		model.addAttribute("quizId", quiz.getId());
		model.addAttribute("title", quiz.getTitle());
		model.addAttribute("datePublication", quiz.getPublicationDate().toLocalDate());
		return "public/createQuiz";
	}
	@PostMapping("/public/createQuiz")
	public String submitFormQuiz(Integer id, String title, String publicationDate) {
		LocalDate pubDate = LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (id == null) {
			this.service.createQuiz(title, pubDate);
		} else {
			this.service.update(id, title, pubDate);
		}
		return "public/createQuestion";
	}

	@GetMapping("/public/homeManager")
	public String listQuizCreate(Model model) {
		model.addAttribute("listQuiz", this.service.getAll());
		return "public/homeManager";
	}

}
