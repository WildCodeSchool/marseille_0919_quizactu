package fr.actuz.quizactu.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Account;

/**
 *
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	Account findOneByUserName(final String userName);

	// Find user and score order by score
	List<Account> findTop10ByRoleNameOrderByScoreDesc(String name);

	// Find all user on page ranking;
	List<Account> findByRoleNameOrderByScoreDesc(String name);

}