package org.example.examen_citoyennete.repository.question;

import org.example.examen_citoyennete.model.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Primary
public class MockedQuestionRepository implements IQuestionRepository {

    private List<Question> questions = new ArrayList<>();


    MockedQuestionRepository(){
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME2, Level.LEVEL0));
        questions.add(new Question(Theme.THEME2, Level.LEVEL1));
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
        return Optional.of(questions.stream().filter(q -> q.getTheme().equals(theme) && q.getLevel().equals(level)).toList());
    }
}
