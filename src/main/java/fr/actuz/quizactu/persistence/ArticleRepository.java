package fr.actuz.quizactu.persistence;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.actuz.quizactu.business.entity.Article;


/**
 * 
 */
public interface ArticleRepository extends JpaRepository<Article, Integer>{

}