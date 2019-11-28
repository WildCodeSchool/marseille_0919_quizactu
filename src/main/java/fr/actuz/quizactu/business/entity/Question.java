package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@Lob
	private String content;

	/**
	 *
	 */
	@Lob
	@Column(length = 20000000) // 20Mo
	private byte[] image;

	/**
	 *
	 */
	@Column(name = "TIMER_QUESTION")
	private Integer timerQuestion;


	/**
	 *
	 */
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Response> responses = new ArrayList<>();

	/**
	 *
	 */
	@ManyToOne
	@JoinColumn(name = "QUIZ_ID", nullable = false)
	private Quiz quiz;

	/**
	 *
	 */
	@OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
	private Article article;

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

	public byte[] getImage() {
		return this.image;

	}

	public String getImageEncoded() {
		Encoder encoder = Base64.getEncoder();
		return "data:image/png;base64," + encoder.encodeToString(this.image);
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getTimerQuestion() {
		return this.timerQuestion;
	}

	public void setTimerQuestion(Integer timerQuestion) {
		this.timerQuestion = timerQuestion;
	}

	public List<Response> getResponses() {
		return this.responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Response> getCorrectResponses() {
		List<Response> result = new ArrayList<>();
		for (Response resp : this.responses) {
			// Chercher la bonne réponse et si trouvée remplir result.
			if (resp.getIsTrue()) {
				result.add(resp);
			}
		}
		return result;
	}

}