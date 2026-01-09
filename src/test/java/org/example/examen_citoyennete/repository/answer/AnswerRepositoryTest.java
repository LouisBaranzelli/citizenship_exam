package org.example.examen_citoyennete.repository.answer;

import jakarta.transaction.Transactional;
import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.answertranslation.AnswerTranslationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository repository;

    @Autowired
    AnswerTranslationRepository translationRepository;



    @Test
    @Transactional
    public void test(){
        Answer q1 = new Answer();
        repository.save(q1);
        List<Answer> questionSaved = repository.findAll();
        assertEquals(1, questionSaved.size());

        List<AnswerTranslation> answerTranslationSaved = translationRepository.findAll();
        assertEquals(0, answerTranslationSaved.size());
        AnswerTranslation qt1 = new AnswerTranslation();
        AnswerTranslation qt2 = new AnswerTranslation();

        // pas suffisant pour que la fk soit set
        q1.getAnswersTranslations().addAll(List.of(qt1, qt2));

        // set la la fk
        qt1.setAnswer(q1);
        qt2.setAnswer(q1);
        repository.save(q1);

        answerTranslationSaved = translationRepository.findAll();
        assertEquals(2, answerTranslationSaved.size());

        repository.delete(q1);
        answerTranslationSaved = translationRepository.findAll();
        assertEquals(0, answerTranslationSaved.size());

    }

}