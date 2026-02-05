package org.example.examen_citoyennete.repository.question;

import jakarta.transaction.Transactional;
import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.questionstranslation.QuestionTranslationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class QuestionRepositoryTest {

    @Autowired
    QuestionRepository repository;

    @Autowired
    QuestionTranslationRepository translationRepository;


    /*
    Transactional permet de garder q1 managed durant toute la transaction
    et d'ajouter qt1 et qt2 sur un une entity managed
     */
    @Test
    @Transactional
    public void test(){
        Question q1 = new Question(Theme.T1, Level.L1);
        repository.save(q1);
        List<Question> questionSaved = repository.findAll();
        assertEquals(1, questionSaved.size());

        List<QuestionTranslation> questionTranslatedSaved = translationRepository.findAll();
        assertEquals(0, questionTranslatedSaved.size());
        QuestionTranslation qt1 = new QuestionTranslation();
        QuestionTranslation qt2 = new QuestionTranslation();

        // pas suffisant pour que la fk soit set
        q1.getQuestionTranslations().addAll(List.of(qt1, qt2));

        // set la la fk
        qt1.setQuestion(q1);
        qt2.setQuestion(q1);
        repository.save(q1);

        questionTranslatedSaved = translationRepository.findAll();
        assertEquals(2, questionTranslatedSaved.size());

        repository.delete(q1);
        questionTranslatedSaved = translationRepository.findAll();
        assertEquals(0, questionTranslatedSaved.size());

    }


}