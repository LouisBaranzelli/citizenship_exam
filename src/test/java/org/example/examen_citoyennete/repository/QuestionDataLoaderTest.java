package org.example.examen_citoyennete.repository;

import org.example.examen_citoyennete.repository.question.IQuestionRepository;
import org.example.examen_citoyennete.repository.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

class QuestionDataLoaderTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    public void test(){
        QuestionDataLoader questionDataLoader = new QuestionDataLoader(questionRepository);
    }
}