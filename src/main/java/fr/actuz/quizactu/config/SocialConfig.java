package fr.actuz.quizactu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void addConnectionFactories(
			ConnectionFactoryConfigurer connectionFactoryConfigurer,
			Environment environment) {
		connectionFactoryConfigurer
				.addConnectionFactory(new FacebookConnectionFactory(
						environment.getProperty(
								"spring.social.facebook.appId"),
						environment.getProperty(
								"spring.social.facebook.appSecret")));
		connectionFactoryConfigurer
				.addConnectionFactory(new GoogleConnectionFactory(
						environment
								.getProperty("spring.social.google.appId"),
						environment.getProperty(
								"spring.social.google.appSecret")));
		connectionFactoryConfigurer
				.addConnectionFactory(new LinkedInConnectionFactory(
						environment.getProperty(
								"spring.social.linkedin.appId"),
						environment.getProperty(
								"spring.social.linkedin.appSecret")));
		super.addConnectionFactories(connectionFactoryConfigurer,
				environment);
	}

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(
			ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(this.dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
	}

	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}
}
