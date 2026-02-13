package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.controller.QuestionNotFoundException;
import org.example.examen_citoyennete.model.Language;
import org.example.examen_citoyennete.model.Level;
import org.example.examen_citoyennete.model.Theme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void test() throws QuestionNotFoundException {
        assertNotNull(questionService.getRandomQuestion(Level.L1, Theme.T1, Language.FR, Collections.emptyList()));
        assertNotNull(questionService.getRandomQuestion(Level.L1, Theme.T1, Language.FR, List.of(1L)));
        assertThrowsExactly(QuestionNotFoundException.class, () -> questionService.getRandomQuestion(Level.L1, Theme.T1, Language.FR, List.of(0L)));
    }

    @Test
    void getQuestion() throws QuestionNotFoundException {
        assertNotNull(questionService.getQuestion(0L, Language.FR));
        assertThrowsExactly(QuestionNotFoundException.class, () ->questionService.getQuestion(0L, Language.RU));

    }

    @Test
    void getRandomPathThemeImages() {
        assertNotEquals(null, questionService.getRandomPathThemeImages(Theme.T1));
    }
}