package org.example.examen_citoyennete.repository.question;

import org.example.examen_citoyennete.model.*;

import java.util.List;
import java.util.Optional;

public interface IQuestionRepository {
    List<Question> findAll();
    Optional<Question> findById(Long id);
    Optional<List<Question>> findByThemeAndLevel(Theme theme, Level level);
}
