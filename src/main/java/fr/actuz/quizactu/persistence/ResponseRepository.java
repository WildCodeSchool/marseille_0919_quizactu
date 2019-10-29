package fr.actuz.quizactu.persistence;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.actuz.quizactu.business.entity.Response;

/**
 * 
 */
public interface ResponseRepository extends JpaRepository<Response, Integer>{

}