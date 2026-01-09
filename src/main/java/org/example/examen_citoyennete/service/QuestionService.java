package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.question.IQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {

    IQuestionRepository repository;

    public QuestionService(IQuestionRepository questionRepository){
        this.repository = questionRepository;
    }

    public Question getRandomQuestion(Level level, Theme theme){
       List<Question> questions = repository.findByThemeAndLevel(theme, level).orElse(Collections.emptyList());
       if (questions.isEmpty()){
           return null;
       }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }


}
