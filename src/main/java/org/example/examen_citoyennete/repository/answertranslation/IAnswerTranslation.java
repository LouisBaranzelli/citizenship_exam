package org.example.examen_citoyennete.repository.answertranslation;

import org.example.examen_citoyennete.model.*;

public interface IAnswerTranslation {
    AnswerTranslation findByAnswerAndLanguage(Answer answer, Language language);
}
