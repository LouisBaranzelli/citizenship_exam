package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.controller.QuestionNotFoundException;
import org.example.examen_citoyennete.model.Language;
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
    public void test() throws QuestionNotFoundException {
        assertNotNull(questionService.getRandomQuestion(Level.L2, Theme.T2, Language.FR));
    }

    @Test
    void getQuestion() throws QuestionNotFoundException {
        assertNotNull(questionService.getQuestion(0L, Language.FR));
        assertThrowsExactly(QuestionNotFoundException.class, () ->questionService.getQuestion(0L, Language.RU));

    }
}