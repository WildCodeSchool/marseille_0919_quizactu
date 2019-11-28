package fr.actuz.quizactu.business.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Role;
import fr.actuz.quizactu.persistence.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	public List<Account> getAll() {
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

	public Account getById(Integer id) {
		return this.accountRepo.getOne(id);
	}

	public List<Account> getScoreLimitTen() {
		return this.accountRepo.findTop10ByRoleNameOrderByScoreDesc("USER");
	}

	public List<Account> getScoreOnRankingPage() {
		return this.accountRepo.findByRoleNameOrderByScoreDesc("USER");
	}
	

	public Account updateAvatar(int id, MultipartFile avatar) {
		Account acc = this.accountRepo.getOne(id);
		if (!avatar.getOriginalFilename().isEmpty()) {
			try {
				acc.setAvatar(avatar.getBytes());
				this.accountRepo.save(acc);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return this.accountRepo.save(acc);
	}

	public void updatePassword(int id, String password) {
		Account acc = this.accountRepo.getOne(id);
		final String hashedPassword = this.bCrypt.encode(password);
		acc.setPassword(hashedPassword);
		this.accountRepo.save(acc);
	}

}
