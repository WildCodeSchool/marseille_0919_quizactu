package fr.actuz.quizactu.persistence;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Account;


/**
 * 
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	UserDetails findOneByUserName(final String userName);

}