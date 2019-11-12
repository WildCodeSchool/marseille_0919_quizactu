package fr.actuz.quizactu.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Role;
import fr.actuz.quizactu.persistence.AccountRepository;

@Service
public class SocialConnectionSignup implements ConnectionSignUp {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public String execute(Connection<?> connection) {
		System.out.println("----------------- CONN KEY"
				+ connection.getKey() + " --------------------");
		Account user = new Account();
		user.setUserName(connection.getDisplayName());
		user.setPassword(RandomStringUtils.randomAlphabetic(8));
		user.setRole(new Role(3));
		this.accountRepository.save(user);
		return user.getUsername();
	}
}