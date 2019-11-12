package fr.actuz.quizactu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private SocialConnectionSignup facebookConnectionSignup;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().permitAll().loginPage("/login").and().logout()
				.permitAll().and().authorizeRequests()
				.expressionHandler(this.webExpressionHandler())
				.antMatchers("/public/**", "/webjars/**", "/images/**",
						"/css/**", "/signin/**", "/signup/**", "/auth/**")
				.permitAll().anyRequest().authenticated().and()
				.apply(new SpringSocialConfigurer());
	}

	@Bean
	public ProviderSignInController providerSignInController(
			ConnectionFactoryLocator connectionLocator,
			UsersConnectionRepository connectionRepo) {
		((JdbcUsersConnectionRepository) connectionRepo)
				.setConnectionSignUp(this.facebookConnectionSignup);

		ProviderSignInController providerSignInController = new ProviderSignInController(
				connectionLocator, connectionRepo,
				new SocialSignInAdapter());
		return providerSignInController;
	}

	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy
				.setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
		return roleHierarchy;
	}

	private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
		DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		defaultWebSecurityExpressionHandler
				.setRoleHierarchy(this.roleHierarchy());
		return defaultWebSecurityExpressionHandler;
	}
}
