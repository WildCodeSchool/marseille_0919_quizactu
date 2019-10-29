package fr.actuz.quizactu.business.entity;

import java.util.*;

/**
 * 
 */
public class Question {

    /**
     * Default constructor
     */
    public Question() {
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String image;

    /**
     * 
     */
    private Integer timerQuestion;

    /**
     * 
     */
    private Integer timerResponse;

    /**
     * 
     */
    private List<Response> responses;

    /**
     * 
     */
    private Article article;

}