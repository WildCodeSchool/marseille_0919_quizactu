package fr.actuz.quizactu.business.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
		return this.quizRepo.getOne(id);
	}

	public Quiz getTodayQuiz() {
		return this.quizRepo.findOneByPublicationDate(LocalDate.now().atStartOfDay().atZone(ZoneId.of("UTC")));
	}

	public Quiz getYesterdayQuiz() {
		return this.quizRepo
				.findOneByPublicationDate(LocalDate.now().minusDays(1).atStartOfDay().atZone(ZoneId.of("UTC")));
	}

	public Quiz getDayBeforeYesterdayQuiz() {
		return this.quizRepo
				.findOneByPublicationDate(LocalDate.now().minusDays(2).atStartOfDay().atZone(ZoneId.of("UTC")));
	}

	public void getPoints(Integer accountId, Integer responseId) {
		Response resp = this.getResponseById(responseId);
		Account acc = this.accountRepo.getOne(accountId);
		Integer score = 0;
		// Si la réponse choisie est juste, incrémente le score de 10
		if (resp.getIsTrue()) {
			score += 10;
		}
		// Si le score de l'user est null, set son score avec celui de la variable score
		if (acc.getScore() == null) {
			acc.setScore(score);
			this.accountRepo.save(acc);
		} else {
			acc.setScore(score + acc.getScore());
			this.accountRepo.save(acc);
		}
	}

	public Response getResponseById(Integer id) {
		Response resp = this.responseRepo.getOne(id);
		return resp;
	}
	
	public List<Response> getAllResp(int questionId) {
		return this.responseRepo.findAllResponseByQuestionId(questionId); 
	}

	public List<Quiz> getAll() {
		return this.quizRepo.findAll();
	}

//	public Quiz create(Integer id) {
//		Quiz quiz = new Quiz();
//		quiz.setId(this.quizRepo.getOne(id));
//		return this.quizRepo.save(quiz);
//	}

	public Quiz read(int id) {
		return this.quizRepo.getOne(id);
	}

	public Quiz update(Integer id, String title, LocalDate creationDate, ZonedDateTime publicationDate) {
		Quiz quiz = this.read(id);
		quiz.setTitle(title);
		quiz.setCreationDate(creationDate);
		quiz.setPublicationDate(publicationDate);
		return this.quizRepo.save(quiz);
	}

	public void delete(int id) {
		this.quizRepo.deleteById(id);
	}

	public void createQuiz(String title, ZonedDateTime publicationDate, List<Question> questions) {
		Quiz quiz = new Quiz(title, publicationDate, questions);
		this.quizRepo.save(quiz);
	}

}
