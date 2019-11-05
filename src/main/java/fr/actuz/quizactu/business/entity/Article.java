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

}