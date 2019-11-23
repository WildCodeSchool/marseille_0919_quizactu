package fr.actuz.quizactu.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.business.service.ArticleService;
import fr.actuz.quizactu.business.service.QuizService;

@Controller
@RequestMapping("/manager")
@SessionAttributes({ "quizId" })
@Scope("session")
public class ManageQuizController {

	@Autowired
	private QuizService service;

	@Autowired
	private ArticleService articleService;

	@ModelAttribute("quizId")
	public Integer quizId() {
		return null;
	}

	@GetMapping("/home")
	public String listQuizCreate(Model model) {
		model.addAttribute("listQuiz", this.service.getAll());
		return "manager/home";
	}

	@GetMapping("/listResponses/{questionId}")
	public String listResp(@PathVariable int questionId, Model model) {
		model.addAttribute("listResp", this.service.getAllResp(questionId));
		return "manager/listResponses";
	}

	@GetMapping("/quizDetails/{id}")
	public String getQuestions(Model model, @PathVariable Integer id) {
		Quiz quiz = this.service.read(id);
		model.addAttribute("quiz", quiz);
		model.addAttribute("quizId", quiz.getId());
		return "manager/quizDetails";
	}

	@PostMapping("/setQuestion/{questionId}")
	public String submitUpdateQuestion(@PathVariable Integer questionId, String content, Integer timerQuestion,
			Integer timerResponse, MultipartFile image, @ModelAttribute("quizId") Integer quizId) {
		this.service.updateQuestion(questionId, content, timerQuestion, timerResponse, image);
		return "redirect:/manager/quizDetails/" + quizId;
	}

	@GetMapping("/createQuiz")
	public String showFormQuiz() {
		return "manager/createQuiz";
	}

	@GetMapping("/modifyQuiz/{quizId}")
	public String showModifyQuiz(@PathVariable Integer quizId, Model model) {
		Quiz quiz = this.service.read(quizId);
		model.addAttribute("quizId", quiz.getId());
		model.addAttribute("title", quiz.getTitle());
		model.addAttribute("datePublication", quiz.getPublicationDate().toLocalDate());
		return "manager/createQuiz";
	}

	@PostMapping("/createQuiz")
	public String submitFormQuiz(Integer id, String title, String publicationDate, Model model) {
		LocalDate pubDate = LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		if (id == null) {
			Quiz quiz = this.service.createQuiz(title, pubDate);
			model.addAttribute("quizId", quiz.getId());
			return "redirect:/manager/createQuestion/" + quiz.getId();
		} else {
			this.service.update(id, title, pubDate);
			return "redirect:/manager/home";
		}
	}

	@GetMapping("/createQuestion/{quizId}")
	public String showFormQuestion(@PathVariable Integer quizId, Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("quizId", quizId);
		return "manager/createQuestion";
	}

	@PostMapping("/createQuestion/{quizId}")
	public String submitFormQuestion(@PathVariable Integer quizId, Question question,
			@RequestParam MultipartFile questionImage) {
		question = this.service.createQuestion(quizId, question, questionImage);
		return "redirect:/manager/createResponse/" + question.getId();
	}

	@GetMapping("/createResponse/{questionId}")
	public String showFormResponse(@PathVariable Integer questionId, Model model,
			@ModelAttribute("quizId") Integer quizId) {
		model.addAttribute("response", new Response());
		model.addAttribute("questionId", questionId);
		model.addAttribute("quizId", quizId);
		return "manager/createResponse";
	}

	@PostMapping("/createResponse/{questionId}")
	public String submitFormResponse(@PathVariable Integer questionId, Response response, Model model) {
		this.service.createResponse(questionId, response);
		model.addAttribute("response", new Response());
		return "manager/createResponse";
	}

	@PostMapping("/setArticle/{articleId}")
	public String submitUpdateArticle(@PathVariable Integer articleId, String title, String summary, String media,
			String link, @ModelAttribute("quizId") Integer quizId) {
		this.articleService.update(articleId, title, summary, media, link);
		return "redirect:/manager/quizDetails/" + quizId;
	}

	@PostMapping("/setResponse/{responseId}")
	public String submitUpdateResponse(@PathVariable Integer responseId, String content, Boolean radioIsTrue,
			@ModelAttribute("quizId") Integer quizId) {
		this.service.updateResponse(responseId, content, radioIsTrue);
		return "redirect:/manager/quizDetails/" + quizId;
	}

	@PostMapping("/setQuiz/{quizId}")
	public String submitUpdateQuiz(@PathVariable Integer quizId, String title, String publicationDate) {
		LocalDate publicationDateParsed = LocalDate.parse(publicationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		this.service.update(quizId, title, publicationDateParsed);
		return "redirect:/manager/home";
	}

	@GetMapping("/deleteQuiz/{quizId}")
	public String deleteQuiz(@PathVariable Integer quizId) {
		this.service.delete(quizId);
		return "redirect:/manager/home";
	}

	@GetMapping("/deleteQuestion/{questionId}")
	public String deleteQuestion(@PathVariable Integer questionId, @ModelAttribute("quizId") Integer quizId) {
		this.service.deleteQuestion(questionId);
		return "redirect:/manager/quizDetails/" + quizId;
	}

	@GetMapping("/deleteResponse/{responseId}")
	public String deleteResponse(@PathVariable Integer responseId, @ModelAttribute("quizId") Integer quizId) {
		this.service.deleteResponse(responseId);
		return "redirect:/manager/quizDetails/" + quizId;
	}

	@GetMapping("/deleteArticle/{articleId}")
	public String deleteArticle(@PathVariable Integer articleId, @ModelAttribute("quizId") Integer quizId) {
		this.articleService.delete(articleId);
		return "redirect:/manager/quizDetails/" + quizId;
	}
}
