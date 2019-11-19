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
import fr.actuz.quizactu.persistence.QuizRecordRepository;

@Service
public class QuizRecordService {

	@Autowired
	private QuizRecordRepository recordRepo;

	@Autowired
	private QuizService quizService;

	@Autowired
	private AccountRepository accountRepo;

	// Save result quiz
	public void recordResultQuiz(Integer quizId, Integer responseId, Integer accountId) {
		Quiz quiz = this.quizService.getQuizById(quizId);
		Response resp = this.quizService.getResponseById(responseId);
		Account acc = this.accountRepo.getOne(accountId);
		QuizRecord quizRecord = new QuizRecord(quiz, resp, acc);
		this.recordRepo.save(quizRecord);
	}

	// Method for get the points of quiz that day
	public Integer getScoreQuiz(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		Integer score = 0;
		for (QuizRecord result : quizRecord) {
			if (result.getResponse().getIsTrue()) {
				score += 10;
			}
		}
		return score;
	}

	public List<Response> getQuizResponses(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		List<Response> responses = new ArrayList<>();

		for (QuizRecord result : quizRecord) {
			responses.add(result.getResponse());
		}

		return responses;
	}

	public List<QuizRecord> getByQuizIdAndAccountId(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		return quizRecord;
	}

	public boolean compareIfQuestionAlreadyAnswered(Integer quizId, Integer accountId, Integer responseId,
			Question curQuestion) {
		List<QuizRecord> records = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		Response responseRecord;
		for (QuizRecord quizRecord : records) {
			responseRecord = this.quizService.getResponseById(quizRecord.getResponse().getId());
			if (responseRecord.getQuestion().getId() == curQuestion.getId()) {
				return false;
			}
		}
		return true;
	}

}
