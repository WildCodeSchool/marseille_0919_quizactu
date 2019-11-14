package fr.actuz.quizactu.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.QuizRecord;
import fr.actuz.quizactu.business.entity.Response;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.QuizRecordRepository;
import fr.actuz.quizactu.persistence.QuizRepository;

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
		Quiz quiz = quizService.getQuizById(quizId);
		Response resp = quizService.getResponseById(responseId);
		Account acc = this.accountRepo.getOne(accountId);
		QuizRecord quizRecord = new QuizRecord(quiz, resp, acc);
		this.recordRepo.save(quizRecord);
	}

	// Method for get the points of quiz that day
	public Integer getScoreQuiz(Integer quizId, Integer accountId) {
		List<QuizRecord> quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		Integer score = 0;
		for (QuizRecord result : quizRecord) {
			if(result.getResponse().getIsTrue()) {
				score += 10;
			}
		}
		return score;
	}
	
}
