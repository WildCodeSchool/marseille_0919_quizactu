package fr.actuz.quizactu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.actuz.quizactu.business.entity.Account;
import fr.actuz.quizactu.business.service.AccountService;
import fr.actuz.quizactu.business.service.ArticleService;

@Controller

public class AccountController {

	@Autowired
	private AccountService service;

	@Autowired
	private ArticleService artService;

	@GetMapping("/changedPassword")
	public String change(Model model, HttpServletRequest request) {
		String user = request.getUserPrincipal().getName();
		Account acc = this.service.read(user);
		model.addAttribute("connectedId", acc.getId());
		return "changedPassword";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	


	@GetMapping("/public/createAccount")
	public String create() {
		return "public/createAccount";
	}

	@PostMapping("/public/form")
	public String save(@Valid Account account, BindingResult result, String confirmPassword) {
		if (result.hasErrors()) {
			return "public/createAccount";
		} else {
			if (confirmPassword.equals(account.getPassword())) {
				this.service.create(account.getUsername(), account.getEmail(), account.getPassword());
			} else {
				return "public/createAccount";
			}
			return "redirect:/";
		}

	}

	@PostMapping("/changedPassword")
	public String newPassword(Integer id, String newPassword) {
		this.service.updatePassword(id, newPassword);
		return "redirect:/";
	}

	@GetMapping("/public/forgotPassword")
	public String forgot() {
		return "public/forgotPassword";
	}
}
