package org.example.examen_citoyennete.repository.question;

import org.example.examen_citoyennete.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class MockedQuestionRepository implements IQuestionRepository {

    private List<Question> questions = new ArrayList<>();


    MockedQuestionRepository(){
        Question question1 = new Question(Theme.T1, Level.L1, 0L);
        question1.getQuestionTranslations().add(new QuestionTranslation(Language.FR, "Quel age as-tu ?", 0L, question1));
        question1.getQuestionTranslations().add(new QuestionTranslation(Language.EN, "How Old are you ?", 1L, question1));

        Answer answer1 = new Answer(question1, true, 0L);
        answer1.getAnswersTranslations().add(new AnswerTranslation(Language.FR, answer1, "j'ai 34 ans", 2L));
        answer1.getAnswersTranslations().add(new AnswerTranslation(Language.EN, answer1, "I am 34", 3L));

        Answer answer2 = new Answer(question1, false, 1L);
        answer2.getAnswersTranslations().add(new AnswerTranslation(Language.FR, answer2, "j'ai 30 ans", 4L));
        answer2.getAnswersTranslations().add(new AnswerTranslation(Language.EN, answer2, "I am 30", 5L));

        question1.getAnswers().addAll(List.of(answer1, answer2));
        questions.add(question1);

        questions.add(new Question(Theme.T1, Level.L2, 1L));
        questions.add(new Question(Theme.T1, Level.L2, 2L));
        questions.add(new Question(Theme.T2, Level.L2, 3L));
        questions.add(new Question(Theme.T2, Level.L2, 4L));
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
