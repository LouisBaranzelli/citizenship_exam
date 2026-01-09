package org.example.examen_citoyennete.repository.answertranslation;

import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.answer.IAnswerRepository;
import org.example.examen_citoyennete.repository.questionstranslation.IQuestionTranslationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class MockedAnswerTranslationRepository implements IAnswerTranslation {

    List<Answer> answerRepository = new ArrayList<>();

    MockedAnswerTranslationRepository(){

        Question q = new Question(Theme.THEME1, Level.LEVEL0);
        answerRepository.add(new Answer(q, true));
        answerRepository.add(new Answer(q, true));
    }

    @Override
    public AnswerTranslation findByAnswerAndLanguage(Answer answer, Language language) {
        Optional<Answer> requestAnswer =  answerRepository.stream().filter(q -> q.getId().equals(answer.getId())).findFirst();
        return requestAnswer.flatMap(value -> value.getAnswersTranslations().stream().filter(q -> q.language().equals(language)).findFirst()).orElse(null);
    }
}
