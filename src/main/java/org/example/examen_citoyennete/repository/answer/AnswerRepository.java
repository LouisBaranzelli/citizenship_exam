package org.example.examen_citoyennete.repository.answer;

import org.example.examen_citoyennete.model.Answer;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface AnswerRepository extends JpaRepository<Answer, Long>, IAnswerRepository{
}
