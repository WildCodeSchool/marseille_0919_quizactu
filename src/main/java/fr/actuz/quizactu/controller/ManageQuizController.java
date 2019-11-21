package fr.actuz.quizactu.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import fr.actuz.quizactu.business.service.ArticleService;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class ManageQuizController {

	@Autowired
	private QuizService service;
	
	@Autowired
	private ArticleService articleService;

	@GetMapping("/public/homeManager")
	public String listQuizCreate(Model model) {
		model.addAttribute("listQuiz", this.service.getAll());
		return "public/homeManager";
	}

	@GetMapping("/public/listResponses/{questionId}")
	public String listResp(@PathVariable int questionId, Model model) {
		model.addAttribute("listResp", this.service.getAllResp(questionId));
		return "public/listResponses";
	}

	@GetMapping("/public/quizDetails/{id}")
	public String getQuestions(Model model, @PathVariable Integer id) {
		model.addAttribute("quiz", this.service.read(id));
		return "public/quizDetails";
	}

	@PostMapping("/public/setQuestion/{questionId}")
	public String submitUpdateQuestion(@PathVariable Integer questionId, String content, Integer timerQuestion, Integer timerResponse, MultipartFile image) {
		try {
			this.service.updateQuestion(questionId, content, timerQuestion, timerResponse, image.getBytes());
		} catch (IOException e){
			e.printStackTrace();
		}
		return "redirect:/public/homeManager";
	}

	@PostMapping("/public/setResponse/{responseId}")
	public String submitUpdateResponse(@PathVariable Integer responseId, String content) {
		this.service.updateResponse(responseId, content);
		return "redirect:/public/homeManager";
	}
	
	@PostMapping("/public/setArticle/{articleId}")
	public String submitUpdateArticle(@PathVariable Integer articleId, String title, String summary, String media, String link) {
		this.articleService.update(articleId, title, summary, media, link);;
		return "redirect:/public/homeManager";
	}

}
