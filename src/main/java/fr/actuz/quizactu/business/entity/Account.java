package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public Account() {
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
	@ManyToOne 
    @JoinColumn(name="ROLE_ID", nullable=false)
	private Role role;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private Integer score;

	/**
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "ACCOUNT_has_ARTICLE")
	private List<Article> articles;

}