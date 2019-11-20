package fr.actuz.quizactu.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 */
@Entity
@Table(name = "RESPONSE")
public class Response implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Response() {
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
	private Boolean isTrue;

	@Transient
	private boolean notAnswered;

	/**
	 *
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsTrue() {
		return this.isTrue;
	}

	public void setIsTrue(Boolean isTrue) {
		this.isTrue = isTrue;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isNotAnswered() {
		return this.notAnswered;
	}

	public void setNotAnswered(boolean notAnswered) {
		this.notAnswered = notAnswered;
	}

}