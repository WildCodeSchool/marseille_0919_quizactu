package fr.actuz.quizactu.persistence;

import java.time.ZonedDateTime;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Quiz;
import fr.actuz.quizactu.business.entity.Response;

/**
 * 
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer>{
	 List<Response>findAllResponseByQuestionId(Integer questionId);
}