package fr.actuz.quizactu.business.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Question;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.QuizRecord;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.QuestionRepository;
import fr.actuz.quizactu.persistence.QuizRecordRepository;
import fr.actuz.quizactu.persistence.ResponseRepository;

@Service
public class QuizRecordService {

	@Autowired
	private QuizRecordRepository recordRepo;

	@Autowired
	private QuizService quizService;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ResponseRepository responseRepository;

	// Save result quiz
	public void recordResultQuiz(Integer quizId, Integer questionId, Integer responseId, Integer accountId) {
		Quiz quiz = this.quizService.getQuizById(quizId);
		Question question = this.questionRepository.getOne(questionId);
		Response resp = null;
		// Ne chercher la réponse en BDD que si l'utilisateur à bien donné une réponse.
		// Sinon le timer est passé à la suite avant qu'il choisisse une réponse, on
		// laisse donc la relation null.
		if (responseId != null) {
			resp = this.quizService.getResponseById(responseId);
		}
		Account acc = this.accountRepo.getOne(accountId);
		QuizRecord quizRecord = new QuizRecord(quiz, question, resp, acc);
		this.recordRepo.save(quizRecord);
	}

	public void updateResultQuiz(Integer questionId, Integer accountId, Integer responseId) {
		QuizRecord record = this.recordRepo.findOneByQuestionIdAndAccountId(questionId, accountId);
		Response response = null;
		if (responseId != null) {
			response = this.quizService.getResponseById(responseId);
		}
		record.setResponse(response);
		this.recordRepo.save(record);
	}

	// Method for get the points of quiz that day
	public Integer getScoreQuiz(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		Integer score = 0;
		for (QuizRecord result : quizRecord) {
			if (result.getResponse() != null && result.getResponse().getIsTrue()) {
				score += 10;
			}
		}
		return score;
	}

	public List<Response> getQuizResponses(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		List<Response> responses = new ArrayList<>();

		for (QuizRecord result : quizRecord) {
			if (result.getResponse() != null) {
				responses.add(result.getResponse());
			} else {
				Response validResponse = this.responseRepository
						.findAllByQuestionIdAndIsTrueTrue(result.getQuestion().getId()).get(0);
				validResponse.setNotAnswered(true);
				responses.add(validResponse);
			}
		}

		return responses;
	}

	public List<QuizRecord> getByQuizIdAndAccountId(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		return quizRecord;
	}

	public boolean compareIfQuestionAlreadyAnswered(Integer quizId, Integer accountId, Integer questionId) {
		return !this.recordRepo.existsByAccountIdAndQuizIdAndQuestionId(accountId, quizId, questionId);
	}
	
	public Boolean hasQuizAlreadyBeenPlayed(Integer quizId) {
		return this.recordRepo.existsByQuizId(quizId);
	}
	
	public Boolean hasQuestionAlreadyBeenAnswered(Integer questionId) {
		return this.recordRepo.existsByQuestionId(questionId);
	}
	
	public List<QuizRecord> getAll(){
		List<QuizRecord> records = this.recordRepo.findAll();
		return records;
	}

}
