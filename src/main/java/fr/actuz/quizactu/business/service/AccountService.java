package fr.actuz.quizactu.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.persistence.AccountRepository;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return this.accountRepo.findOneByUserName(userName);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	public List<Account> getAll(){
		return this.accountRepo.findAll();
	}
	
	public void create(String userName, String email, String password) {
		this.accountRepo.save(new Account(userName, email, password));
	}
	
	public Account read(int id) {
		return this.accountRepo.getOne(id);
	}
	
	public void update(Account account) {
		this.accountRepo.save(account);
	}
	
	public void delete(int id) {
		this.accountRepo.deleteById(id);
	}

	
	
	
	
	
	
	

	
}
