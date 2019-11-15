package fr.actuz.quizactu.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Article;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepo;

	@Autowired
	private AccountRepository accountRepo;

	public boolean favoriteArticle(Integer accountId, Integer articleId) {
		Article art = this.articleRepo.getOne(articleId);
		Account acc = this.accountRepo.getOne(accountId);
		
		if (acc.getArticles().contains(art)) {
			acc.getArticles().remove(art);
			this.accountRepo.save(acc);
			return false;
		} else {
			acc.getArticles().add(art);
			this.accountRepo.save(acc);
			return true;
		}
	}
//
//	public List<Article> displayArticleFavorite(Integer articleId, Integer accountId) {
//		List<Article> art = this.articleRepo.;
//		return art;
//
//	}
}