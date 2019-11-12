package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public Integer getTimerQuestion() {
		return timerQuestion;
	}

	public void setTimerQuestion(Integer timerQuestion) {
		this.timerQuestion = timerQuestion;
	}

	public Integer getTimerResponse() {
		return timerResponse;
	}

	public void setTimerResponse(Integer timerResponse) {
		this.timerResponse = timerResponse;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}