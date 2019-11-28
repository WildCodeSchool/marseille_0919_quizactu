package fr.actuz.quizactu.business.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.QuestionRepository;
import fr.actuz.quizactu.persistence.QuizRepository;
import fr.actuz.quizactu.persistence.ResponseRepository;

@Service
public class QuizService {

	@Autowired
	private QuizRepository quizRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@Autowired
	private ResponseRepository responseRepo;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private QuizRecordService recordService;

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
		List<Quiz> quiz = this.quizRepo.findAll();
		return quiz;
	}

	public List<Quiz> getAllManager() {
		List<Quiz> quizList = this.quizRepo.findAll();
		for (Quiz quiz : quizList) {
			if (this.recordService.hasQuizAlreadyBeenPlayed(quiz.getId())) {
				quiz.setNotEditable(true);
			} else {
				quiz.setNotEditable(false);
			}
		}
		Collections.reverse(quizList);
		return quizList;
	}

	public Quiz read(int id) {
		return this.quizRepo.getOne(id);
	}

	public Quiz update(Integer id, String title, LocalDate publicationDate) {
		Quiz quiz = null;
		ZonedDateTime zonedDate = publicationDate.atStartOfDay().atZone(ZoneId.of("UTC"));
		if (!this.quizRepo.existsByPublicationDateAndIdNot(zonedDate, id)) {
			quiz = this.read(id);
			quiz.setTitle(title);
			quiz.setPublicationDate(zonedDate);
			quiz = this.quizRepo.save(quiz);
		}
		return quiz;
	}

	public void delete(int id) {
		this.quizRepo.deleteById(id);
	}

	public Quiz createQuiz(String title, LocalDate publicationDate) {
		Quiz quiz = null;
		ZonedDateTime zonedDate = publicationDate.atStartOfDay().atZone(ZoneId.of("UTC"));
		if (!this.quizRepo.existsByPublicationDate(zonedDate)) {
			quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setPublicationDate(zonedDate);
			quiz.setCreationDate(LocalDate.now());
			quiz = this.quizRepo.save(quiz);
		}
		return quiz;
	}

	public Question getQuestionById(Integer id) {
		Question question = this.questionRepo.getOne(id);
		return question;
	}

	public void updateQuestion(Integer questionId, String content, Integer timerQuestion, MultipartFile image) {
		Question question = this.getQuestionById(questionId);
		question.setContent(content);
		question.setTimerQuestion(timerQuestion);
		if (!image.getOriginalFilename().isEmpty()) {
			try {
				question.setImage(image.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.questionRepo.save(question);
	}

	public void deleteQuestion(Integer id) {
		this.questionRepo.deleteById(id);
	}

	public void updateResponse(Integer responseId, String content, Boolean radioIsTrue) {
		Response resp = this.getResponseById(responseId);
		resp.setContent(content);
		resp.setIsTrue(radioIsTrue);
		this.responseRepo.save(resp);
	}

	public Question createQuestion(Integer quizId, Question question, MultipartFile image, Integer timerQuestion) {
		Quiz quiz = this.read(quizId);
		question.setQuiz(quiz);
		question.setTimerQuestion(timerQuestion);
		if (question.getArticle() != null) {
			question.getArticle().setQuestion(question);
		}
		if (!image.getOriginalFilename().isEmpty()) {
			try {
				question.setImage(image.getBytes());
				this.questionRepo.save(question);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return this.questionRepo.save(question);
	}

	public void createResponse(Integer questionId, Response response) {
		Question question = this.questionRepo.getOne(questionId);
		response.setQuestion(question);
		this.responseRepo.save(response);
	}

	public void deleteResponse(Integer id) {
		this.responseRepo.deleteById(id);
	}
}