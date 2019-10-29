package fr.actuz.quizactu.business.entity;

import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Quiz {

    /**
     * Default constructor
     */
    public Quiz() {
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    private LocalDate creationDate;

    /**
     * 
     */
    private LocalDate publicationDate;

    /**
     * 
     */
    private List<Question> questions;

}