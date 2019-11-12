package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "QUESTION")
public class Question implements Serializable {

    /**
	 * Default serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Question() {
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
    private String content;

    /**
     * 
     */
    @Lob
    private Byte[] image;

    /**
     * 
     */
    @Column(name="TIMER_QUESTION")
    private Integer timerQuestion;

    /**
     * 
     */
    @Column(name="TIMER_RESPONSE")
    private Integer timerResponse;

    /**
     * 
     */
    @OneToMany(mappedBy = "question")
    private List<Response> responses = new ArrayList<Response>();

    /**
     * 
     */
    @ManyToOne
    @JoinColumn(name="QUIZ_ID", nullable=false)
    private Quiz quiz;
    
    /**
     * 
     */
    @OneToOne(mappedBy = "question")
    private Article article;

}