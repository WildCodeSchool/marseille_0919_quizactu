package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "QUIZ")
public class Quiz implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Quiz() {
    }

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToMany(mappedBy = "quiz")
    private List<Question> questions = new ArrayList<Question>();

}