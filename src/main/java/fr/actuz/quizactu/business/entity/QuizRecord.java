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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuizRecord() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="QUIZ_ID", nullable=false)
	private Quiz quiz;

	@ManyToOne
	@JoinColumn(name="RESPONSE_ID", nullable=false)
	private Response response;

	@ManyToOne
	@JoinColumn(name="ACCOUNT_ID", nullable=false)
	private Account account;
}
