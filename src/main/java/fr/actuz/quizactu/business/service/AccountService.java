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
	
	@Autowired
	private  BCryptPasswordEncoder bCrypt;

	public List<Account> getAll(){
		return this.accountRepo.findAll();
	}
	
	public void create(String userName, String email, String password) {
		final String hashedPassword = this.bCrypt.encode(password);
		Account account = new Account(userName, email, hashedPassword);
		account.setRole(new Role(3)); // => a utiliser pour les admin et manager
		this.accountRepo.save(account);
	}
	

	public Account read(String userName) {
		return this.accountRepo.findOneByUserName(userName);

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

	public void updatePassword(int id, String password) {
		Account acc = this.accountRepo.getOne(id);
		final String hashedPassword = this.bCrypt.encode(password);
		acc.setPassword(hashedPassword);
//		Account account = new Account (id, hashedPassword);
//		account.setPassword(password);
		this.accountRepo.save(acc);
//		this.accountRepo.findOneByPassword(hashedPassword);
//		this.bCrypt.encode(hashedPassword);
	}
	
}
