package fr.actuz.quizactu.business.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;

@Service
public class AccountService implements UserDetailsService{
	private List<Account> accounts;
	
	
	
	
	
	
	public void create(String userName, String password, String email) {
		
		this.accounts.add(new Account(userName, password, email));
	}






	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
