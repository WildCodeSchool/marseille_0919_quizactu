package fr.actuz.quizactu.business.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

/**
 *
 */
@Entity
@Table(name = "ACCOUNT")
public class Account
		implements Serializable, SocialUserDetails, UserDetails {

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
	@JoinColumn(name = "ROLE_ID", nullable = false)
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

	public Account(Integer id, Role role, String userName, String email,
			String password, Integer score, List<Article> articles) {
		super();
		this.id = id;
		this.role = role;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.score = score;
		this.articles = articles;
	}

	public Account(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Account(int id, String password) {
		this.id = id;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

//	public void setId(Integer id) {
//		this.id = id;
//	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public static long getSerialversionuid() {
		return Account.serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(this.role);
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUserId() {
		return this.userName;
	}

}