package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.model.Level;
import org.example.examen_citoyennete.model.Theme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void test(){
        assertNotNull(questionService.getRandomQuestion(Level.LEVEL1, Theme.THEME2));
    }
}