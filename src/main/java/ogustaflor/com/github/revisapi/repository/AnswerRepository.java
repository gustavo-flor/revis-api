package ogustaflor.com.github.revisapi.repository;

import ogustaflor.com.github.revisapi.entity.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
