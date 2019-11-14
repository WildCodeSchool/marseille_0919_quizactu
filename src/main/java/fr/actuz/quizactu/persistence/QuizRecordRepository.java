package fr.actuz.quizactu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.actuz.quizactu.business.entity.QuizRecord;

@Repository
public interface QuizRecordRepository extends JpaRepository<QuizRecord, Integer>{

}
