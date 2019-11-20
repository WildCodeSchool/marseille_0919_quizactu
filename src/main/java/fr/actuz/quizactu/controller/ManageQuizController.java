package fr.actuz.quizactu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fr.actuz.quizactu.business.service.QuizService;

@Controller
public class ManageQuizController {

	@Autowired
	private QuizService service;

}
