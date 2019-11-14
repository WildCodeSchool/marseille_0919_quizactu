package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "ARTICLE")
public class Article implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Article() {
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
	private String summary;

	/**
	 * 
	 */

	private String media;

	/**
	 * 
	 */
	private String link;

	/**
	 * 
	 */
	@OneToOne
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;

	/**
	 * 
	 */
	@ManyToMany(mappedBy = "articles")
	private List<Account> accounts = new ArrayList<Account>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	

}