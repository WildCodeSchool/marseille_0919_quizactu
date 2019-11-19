package fr.actuz.quizactu.persistence;

import java.time.ZonedDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Quiz;

/**
 *
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
	Quiz findOneByPublicationDate(ZonedDateTime zonedDateTime);

}