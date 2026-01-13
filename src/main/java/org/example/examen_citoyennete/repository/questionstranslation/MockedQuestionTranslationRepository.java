package org.example.examen_citoyennete.repository.questionstranslation;

import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.question.MockedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class MockedQuestionTranslationRepository implements IQuestionTranslationRepository{

    @Autowired
    MockedQuestionRepository mockedQuestionRepository;

    @Override
    public QuestionTranslation findByQuestionAndLanguage(Question question, Language language) {
        Optional<Question> requestQuestion =  mockedQuestionRepository.findById(question.getId());
        return requestQuestion.flatMap(value -> value.getQuestionTranslations().stream().filter(q -> q.language().equals(language)).findFirst()).orElse(null);
    }

}
