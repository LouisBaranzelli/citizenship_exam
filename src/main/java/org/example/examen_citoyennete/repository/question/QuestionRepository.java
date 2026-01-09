package org.example.examen_citoyennete.repository.question;

import org.example.examen_citoyennete.model.Level;
import org.example.examen_citoyennete.model.Question;
import org.example.examen_citoyennete.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, IQuestionRepository {
}
