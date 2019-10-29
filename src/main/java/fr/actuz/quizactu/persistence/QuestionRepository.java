package fr.actuz.quizactu.persistence;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.actuz.quizactu.business.entity.Question;

/**
 * 
 */
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}