package org.example.examen_citoyennete.repository.answertranslation;

import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.answer.IAnswerRepository;
import org.example.examen_citoyennete.repository.answer.MockedAnwserRepository;
import org.example.examen_citoyennete.repository.question.MockedQuestionRepository;
import org.example.examen_citoyennete.repository.questionstranslation.IQuestionTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class MockedAnswerTranslationRepository implements IAnswerTranslation {


    @Autowired
    MockedAnwserRepository mockedAnwserRepository;

    @Autowired
    MockedQuestionRepository mockedQuestionRepository;

    @Override
    public AnswerTranslation findByAnswerAndLanguage(Answer answer, Language language) {

        Answer requestAnswer = mockedAnwserRepository.findById(answer.getId());

        return requestAnswer.getAnswersTranslations().stream()
                .filter(a -> a.language().equals(language))
                .findFirst().orElse(null);
    }
}
