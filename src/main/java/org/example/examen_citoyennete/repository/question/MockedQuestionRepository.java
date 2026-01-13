package org.example.examen_citoyennete.repository.question;

import org.example.examen_citoyennete.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
@Primary
public class MockedQuestionRepository implements IQuestionRepository {

    private List<Question> questions = new ArrayList<>();


    MockedQuestionRepository(){
        Question question1 = new Question(Theme.THEME1, Level.LEVEL0, 0L);
        question1.getQuestionTranslations().add(new QuestionTranslation(Language.FR, "Quel age as-tu ?", 0L, question1));
        question1.getQuestionTranslations().add(new QuestionTranslation(Language.EN, "How Old are you ?", 0L, question1));

        Answer answer1 = new Answer(question1, true, 0L);
        answer1.getAnswersTranslations().add(new AnswerTranslation(Language.FR, answer1, "j'ai 34 ans", 0L));
        answer1.getAnswersTranslations().add(new AnswerTranslation(Language.EN, answer1, "I am 34", 0L));

        Answer answer2 = new Answer(question1, false, 0L);
        answer2.getAnswersTranslations().add(new AnswerTranslation(Language.FR, answer1, "j'ai 30 ans", 0L));

        question1.getAnswers().addAll(List.of(answer1, answer2));
        questions.add(question1);

        questions.add(new Question(Theme.THEME1, Level.LEVEL1, 1L));
        questions.add(new Question(Theme.THEME1, Level.LEVEL1, 2L));
        questions.add(new Question(Theme.THEME2, Level.LEVEL1, 3L));
        questions.add(new Question(Theme.THEME2, Level.LEVEL1, 4L));
    }

    @Override
    public List<Question> findAll() {
        return List.copyOf(questions);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questions.stream().filter(q -> q.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<List<Question>> findByThemeAndLevel(Theme theme, Level level) {
        return Optional.of(questions.stream().filter(q -> q.getTheme().equals(theme) && q.getLevel().equals(level)).collect(Collectors.toCollection(ArrayList::new)));
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
