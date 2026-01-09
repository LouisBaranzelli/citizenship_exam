package org.example.examen_citoyennete.repository.answer;

import org.example.examen_citoyennete.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MockedAnwserRepository implements IAnswerRepository{

    private List<Question> questions = new ArrayList<>();

    MockedAnwserRepository(){
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME1, Level.LEVEL0));
        questions.add(new Question(Theme.THEME2, Level.LEVEL0));
        questions.add(new Question(Theme.THEME2, Level.LEVEL1));
    }

    @Override
    public List<Answer> findByQuestion(Question question) {
        return questions.stream()
                .filter(q -> q.getId().equals(question.getId()))
                .findFirst()
                .map(Question::getAnswers)
                .orElse(List.of());
    }
}
