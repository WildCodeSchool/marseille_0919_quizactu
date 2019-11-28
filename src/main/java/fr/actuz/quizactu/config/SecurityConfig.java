package fr.actuz.quizactu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().expressionHandler(this.webExpressionHandler())
				// Paramétrage des accès aux URL de création de quiz seulement pour les profiles
				// utilisateurs avec le role MANAGER.
				.antMatchers("/manager/**").hasAuthority("MANAGER")
				// Paramétrage des accès publiques anonymes (ne nécessite pas d'être connecté.
				.antMatchers("/public/**", "/webjars/**", "/images/**", "/css/**").permitAll()
				// Paramétrage de toutes les autres URL (excepté celles décrites au dessus)
				// seulement pour les utilisateurs authentifiés.
				.anyRequest().authenticated()
				// Paramétrage du moyen d'authentification en formulaire. Success handler pour
				// gérer
				// vers quelle URL rediriger après une authentification valide.
				.and().formLogin().permitAll().loginPage("/login").successHandler(new LoginHandler())
				// Paramétrage du moyen de déconnexion avec redirection vers une route spéciale
				// pour gérer les attributs de session.
				.and().logout().logoutSuccessUrl("/disconnected").permitAll();
	}

	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchy;
	}

	private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler.setRoleHierarchy(this.roleHierarchy());
		return defaultWebSecurityExpressionHandler;
	}
}
