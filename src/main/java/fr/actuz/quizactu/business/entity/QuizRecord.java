package fr.actuz.quizactu.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUIZ_RECORD")

public class QuizRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	public QuizRecord() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "QUIZ_ID", nullable = false)
	private Quiz quiz;

	@ManyToOne
	@JoinColumn(name = "QUESTION_ID")
	private Question question;

	@ManyToOne
	@JoinColumn(name = "RESPONSE_ID")
	private Response response;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	private Account account;

	public QuizRecord(Quiz quiz, Question question, Response response,
			Account account) {
		super();
		this.quiz = quiz;
		this.question = question;
		this.response = response;
		this.account = account;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Response getResponse() {
		return this.response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
