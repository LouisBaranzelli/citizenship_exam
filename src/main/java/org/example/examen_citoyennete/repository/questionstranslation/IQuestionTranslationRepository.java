package org.example.examen_citoyennete.repository.questionstranslation;

import org.example.examen_citoyennete.model.AnswerTranslation;
import org.example.examen_citoyennete.model.Language;
import org.example.examen_citoyennete.model.Question;
import org.example.examen_citoyennete.model.QuestionTranslation;

public interface IQuestionTranslationRepository {
    QuestionTranslation findByQuestionAndLanguage(Question question, Language language);
}
