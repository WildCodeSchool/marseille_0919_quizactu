package fr.actuz.quizactu.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String change(Model model, HttpServletRequest request, MultipartFile avatar) {
		String user = request.getUserPrincipal().getName();
		Account acc = this.service.read(user);
		model.addAttribute("connectedId", acc.getId());
		model.addAttribute("account", acc);
		model.addAttribute("imgAvatar", acc.getAvatarEncoded());
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
//	@PreAuthorize("hasRole('READ_PRIVILEGE')")
	public String newPassword(Integer id, String newPassword) {
		this.service.updatePassword(id, newPassword);
		return "redirect:/";
	}

	@PostMapping("/changedAvatar/")
	public String newAvatar(Principal principal, @RequestParam MultipartFile avatar) {
		Account account = this.service.read(principal.getName());
		this.service.updateAvatar(account.getId(), avatar);
		return "redirect:/";
	}

	@GetMapping("/public/forgotPassword")
	public String forgot() {
		return "public/forgotPassword";
	}
}
