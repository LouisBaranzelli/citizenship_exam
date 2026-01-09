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

    List<Question> questionRepository = new ArrayList<>();

    MockedQuestionTranslationRepository(){

        questionRepository.add(new Question(Theme.THEME1, Level.LEVEL0));
        questionRepository.add(new Question(Theme.THEME1, Level.LEVEL0));
        questionRepository.add(new Question(Theme.THEME1, Level.LEVEL0));
        questionRepository.add(new Question(Theme.THEME2, Level.LEVEL0));
        questionRepository.add(new Question(Theme.THEME2, Level.LEVEL1));
    }


    @Override
    public QuestionTranslation findByQuestionAndLanguage(Question question, Language language) {
        Optional<Question> requestQuestion =  questionRepository.stream().filter(q -> q.getId().equals(question.getId())).findFirst();
        return requestQuestion.flatMap(value -> value.getQuestionTranslations().stream().filter(q -> q.language().equals(language)).findFirst()).orElse(null);
    }


}
