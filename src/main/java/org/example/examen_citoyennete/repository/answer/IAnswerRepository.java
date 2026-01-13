package org.example.examen_citoyennete.repository.answer;

import org.example.examen_citoyennete.model.Answer;
import org.example.examen_citoyennete.model.Language;
import org.example.examen_citoyennete.model.Question;
import org.example.examen_citoyennete.model.QuestionTranslation;

import java.util.List;
import java.util.Optional;

public interface IAnswerRepository {

    List<Answer> findByQuestion(Question question);

    Answer findById(Long id);
}
