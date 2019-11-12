package fr.actuz.quizactu.persistence;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Quiz;

/**
 * 
 */
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

	Quiz findOneByPublicationDateBetween(LocalDate dateBefore, LocalDate dateAfter); 
	
}