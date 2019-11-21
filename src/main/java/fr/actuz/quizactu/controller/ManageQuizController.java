package fr.actuz.quizactu.controller;

import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.actuz.quizactu.business.service.ArticleService;
import fr.actuz.quizactu.business.entity.Article;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
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
			Quiz quiz = this.service.createQuiz(title, pubDate);
			return "redirect:/public/createQuestion/" + quiz.getId();
		} else {
			this.service.update(id, title, pubDate);
			return "redirect:/public/homeManager";
		}
	}
	
	@GetMapping("/public/createQuestion/{quizId}")
	public String showFormQuestion(@PathVariable Integer quizId, Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("quizId", quizId);
		return "public/createQuestion";
	}
	
	@PostMapping("/public/createQuestion/{quizId}")
	public String submitFormQuestion(@PathVariable Integer quizId, Question question, @RequestParam MultipartFile questionImage) { 
		question = this.service.createQuestion(quizId, question, questionImage);
		return "redirect:/public/createResponse/" + question.getId();
	}
	
	@GetMapping("/public/createResponse/{questionId}") 
		public String showFormResponse(@PathVariable Integer questionId, Model model) {
		model.addAttribute("response", new Response());
		model.addAttribute("questionId", questionId);
		return "public/createResponse";
	}

	@PostMapping("public/createResponse/{questionId}")
	public String submitFormResponse(@PathVariable Integer questionId, Response response) {
		this.service.createResponse(questionId, response);
		return "public/createResponse";
	}
	
	@PostMapping("/public/setArticle/{articleId}")
	public String submitUpdateArticle(@PathVariable Integer articleId, String title, String summary, String media, String link) {
		this.articleService.update(articleId, title, summary, media, link);
		return "redirect:/public/homeManager";
	}


	@PostMapping("/public/setResponse/{responseId}")
	public String submitUpdateResponse(@PathVariable Integer responseId, String content, Boolean radioIsTrue) {
		this.service.updateResponse(responseId, content, radioIsTrue);
return "redirect:/public/homeManager";
    }

	@PostMapping("/public/setQuiz/{quizId}")
	public String submitUpdateQuiz(@PathVariable Integer quizId, String title, String publicationDate) {
		LocalDate publicationDateParsed = LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.service.update(quizId, title, publicationDateParsed);

		return "redirect:/public/homeManager";
	}

	@PostMapping("/public/setQuiz/{quizId}")
	public String submitUpdateQuiz(@PathVariable Integer quizId, String title, String publicationDate) {
		LocalDate publicationDateParsed = LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.service.update(quizId, title, publicationDateParsed);
		return "redirect:/public/homeManager";
	}

}
