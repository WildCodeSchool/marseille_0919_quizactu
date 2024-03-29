package fr.actuz.quizactu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Question;

/**
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	List<Question> findAllQuestionByQuizId(Integer quizId);
}