package fr.actuz.quizactu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "fr.actuz.quizactu.controller",
		"fr.actuz.quizactu.persistence",
		"fr.actuz.quizactu.business.service", "fr.actuz.quizactu" }) // => rajouter autres packages du projet
public class QuizactuApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizactuApplication.class, args);
	}

}
