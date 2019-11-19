package fr.actuz.quizactu.business.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.QuizRepository;
import fr.actuz.quizactu.persistence.ResponseRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
	@Autowired
	private ResponseRepository responseRepo;
	
	@Autowired 
	private AccountRepository accountRepo;
	
	
	public Quiz getQuizById(Integer id) {
		return quizRepo.getOne(id);
	}
	
	public Quiz getQuizByPublicationDate() {
		LocalDate today = LocalDate.now();
		Quiz quiz = this.quizRepo.findOneByPublicationDateBetween(today.minusDays(-1), today.plusDays(1));
		return quiz;
	}
	
	public void getPoints(Integer accountId, Integer responseId) {
		Response resp = this.getResponseById(responseId);
		Account acc = this.accountRepo.getOne(accountId);
		Integer score = 0;
		//Si la réponse choisie est juste, incrémente le score de 10
		if (resp.getIsTrue()) {
		 	score += 10;
		}
		//Si le score de l'user est null, set son score avec celui de la variable score
		if (acc.getScore() == null) {
			acc.setScore(score);
			this.accountRepo.save(acc);
		} else {
			acc.setScore(score + acc.getScore());
			this.accountRepo.save(acc);
		}
	}

	public Response getResponseById(Integer id) {
		Response resp = responseRepo.getOne(id);
		return resp;
	}
	
	public void createQuiz(String title, LocalDate publicationDate, List<Question> questions) {
		Quiz quiz = new Quiz(title, publicationDate, questions);
		this.quizRepo.save(quiz);
	}
	
	
}
