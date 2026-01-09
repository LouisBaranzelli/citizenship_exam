package org.example.examen_citoyennete.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.examen_citoyennete.repository.AnswerTranslationRepository;
import org.example.examen_citoyennete.repository.QuestionRepository;
import org.example.examen_citoyennete.repository.QuestionTranslationRepository;
import org.hibernate.loader.ast.internal.SingleUniqueKeyEntityLoaderStandard;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionTranslationRepository questionTranslationRepository;

    @Autowired
    private AnswerTranslationRepository answerTranslationRepository;

    @Test
    void testCreateAndReadQuestion() {

        Logger logger = LoggerFactory.getLogger(QuestionTest.class);

        Question q = new Question();

        Question q2 = new Question();
        q2.setLevel(Level.LEVEL0);
        q2.setTheme(Theme.THEME1);

        QuestionTranslation qt = new QuestionTranslation();
        qt.setQuestion(q);
        qt.setLabelQuestion("Quelle est la capitale de la France ?");
        q.getQuestionTranslations().add(qt);

        AnswerTranslation at = new AnswerTranslation();
        at.setQuestion(q);
        q.getAnswerTranslations().add(at);

        questionRepository.save(q);
        questionRepository.save(q2);
        questionTranslationRepository.save(qt);
        answerTranslationRepository.save(at);

        Question savedQuestion = questionRepository.findById(q.getId()).orElseThrow();
        assertNotEquals(null, savedQuestion);

        List<Question> savedQuestions = questionRepository.findAll();
        assertEquals(2, savedQuestions.size());

        savedQuestions = questionRepository.findQuestionByThemeAndLevel(Theme.THEME1, Level.LEVEL0);
        assertEquals(1, savedQuestions.size());

    }
}