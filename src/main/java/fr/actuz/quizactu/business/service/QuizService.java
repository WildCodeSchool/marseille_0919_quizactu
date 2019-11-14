package fr.actuz.quizactu.business.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.persistence.QuizRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
//	public List<Question> getQuizById(int id) {
//		Quiz quiz = this.quizRepo.getOne(id);
//		List<Question> questions = quiz.getQuestions();
//		return questions;
//	}
	
	public Quiz getQuizByPublicationDate() {
		LocalDate today = LocalDate.now();
		Quiz quiz = this.quizRepo.findOneByPublicationDateBetween(today.minusDays(-1), today.plusDays(1));
		return quiz;
		
		
	}
	
}
