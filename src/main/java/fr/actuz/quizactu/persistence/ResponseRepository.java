package fr.actuz.quizactu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.Response;

/**
 *
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

	Response findOneByQuestionIdAndIsTrueTrue(Integer id);
}