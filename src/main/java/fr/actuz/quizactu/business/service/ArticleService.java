package fr.actuz.quizactu.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.entity.Article;
import fr.actuz.quizactu.persistence.AccountRepository;
import fr.actuz.quizactu.persistence.ArticleRepository;
import fr.actuz.quizactu.persistence.QuestionRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepo;

	@Autowired
	private QuestionRepository questionRepo;

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

	public Article read(Integer id) {
		return this.articleRepo.getOne(id);
	}

	public void update(Integer articleId, String title, String summary, String media, String link) {
		Article article = this.read(articleId);
		article.setTitle(title);
		article.setSummary(summary);
		article.setMedia(media);
		article.setLink(link);
		this.articleRepo.save(article);
	}

	public void delete(Integer id) {
		Article article = this.read(id);
		article.getQuestion().setArticle(null);
		this.questionRepo.save(article.getQuestion());
		this.articleRepo.deleteById(id);
	}
//
//	public List<Article> displayArticleFavorite(Integer articleId, Integer accountId) {
//		List<Article> art = this.articleRepo.;
//		return art;
//
//	}
}
