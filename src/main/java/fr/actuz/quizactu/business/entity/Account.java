package fr.actuz.quizactu.business.entity;

import java.util.*;

/**
 * 
 */
public class Account {

    /**
     * Default constructor
     */
    public Account() {
    }

    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
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
    private List<Article> articles;

}