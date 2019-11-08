package fr.actuz.quizactu.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Role;
import fr.actuz.quizactu.persistence.AccountRepository;

@Service
public class AccountService  {
	
	@Autowired
	private AccountRepository accountRepo;

	public List<Account> getAll(){
		return this.accountRepo.findAll();
	}
	
	public void create(String userName, String email, String password) {
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String hashedPassword = passwordEncoder.encode(password);
		Account account = new Account(userName, email, hashedPassword);
		account.setRole(new Role(3)); // => a utiliser pour les admin et manager
		this.accountRepo.save(account);
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
	
	public List<Account> getScoreLimitTen() {
		return this.accountRepo.findTop10ByOrderByScoreDesc();
	}

	public List<Account> getScoreLimitTen() {
        return this.accountRepo.findAllByOrderByScoreDesc();
    }
}
