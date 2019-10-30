package fr.actuz.quizactu.persistence;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import fr.actuz.quizactu.business.entity.Account;


/**
 * 
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

	UserDetails findOneByUserName(String userName);

}