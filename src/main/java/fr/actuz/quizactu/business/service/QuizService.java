package fr.actuz.quizactu.business.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.persistence.QuizRepository;
import fr.actuz.quizactu.persistence.ResponseRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private ResponseRepository responseRepo;
	
//	public List<Question> getQuizById(int id) {
//		Quiz quiz = this.quizRepo.getOne(id);
//		List<Question> questions = quiz.getQuestions();
//		return questions;
//	}
	
//	public Quiz getQuizByPublicationDate() {
//		LocalDate today = LocalDate.now();
//		Quiz quiz = this.quizRepo.findOneByPublicationDateBetween(today.minusDays(0), today.plusDays(0));
//		return quiz;
//		
//		
//	}
	
	public Quiz getQuizByPublicationDate() {
		LocalDate today = LocalDate.now();
		Quiz quiz = this.quizRepo.findOneByPublicationDateBetween(today.minusDays(1), today.plusDays(1));
		return quiz;
	}
	
	public void getPoints(Account acc, int score) {
		acc.setScore(score + acc.getScore());
	}

	public Response getResponseById(Integer id) {
		Response resp = responseRepo.getOne(id);
		return resp;
	}
}
