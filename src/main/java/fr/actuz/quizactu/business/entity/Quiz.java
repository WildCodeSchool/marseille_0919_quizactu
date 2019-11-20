package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "QUIZ")
public class Quiz implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Quiz() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private LocalDate creationDate;

//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private ZonedDateTime publicationDate;

	@OneToMany(mappedBy = "quiz")
	private List<Question> questions = new ArrayList<>();

	public Quiz(String title, ZonedDateTime publicationDate, List<Question> questions) {
		super();
		this.title = title;
		this.creationDate = LocalDate.now();
		this.publicationDate = publicationDate;
		this.questions = questions;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
		
	}

	public ZonedDateTime getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(ZonedDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}