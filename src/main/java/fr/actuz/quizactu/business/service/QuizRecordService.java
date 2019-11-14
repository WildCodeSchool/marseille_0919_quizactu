package fr.actuz.quizactu.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.QuizRecord;
import fr.actuz.quizactu.persistence.QuizRecordRepository;
import fr.actuz.quizactu.persistence.QuizRepository;

@Service
public class QuizRecordService {
	
	@Autowired
	private QuizRecordRepository recordRepo;
	
	
	//Method for save the points of quiz that day
	public void recordResultQuiz(Integer quizId, Integer responseId, Integer accountId) {
		QuizRecord quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		this.recordRepo.save(quizRecord);
	}

	//Get result quiz
	public QuizRecord getScoreQuiz(Integer quizId, Integer accountId) {
		QuizRecord quizRecord = this.recordRepo.findAllByQuizIdAndAccountId(quizId, accountId);
		return quizRecord;
	}
	
}
