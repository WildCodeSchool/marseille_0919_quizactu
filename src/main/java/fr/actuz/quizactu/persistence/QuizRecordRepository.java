package fr.actuz.quizactu.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.QuizRecord;

@Repository
public interface QuizRecordRepository extends JpaRepository<QuizRecord, Integer>{
	
	List<QuizRecord> findAllByQuizIdAndAccountId(Integer quizId, Integer accountId);
	ArrayList<QuizRecord> findAllByAccountId(Integer accountId);
}
