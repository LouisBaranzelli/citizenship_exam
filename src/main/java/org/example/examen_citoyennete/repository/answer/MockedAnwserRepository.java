package org.example.examen_citoyennete.repository.answer;

import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.question.MockedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MockedAnwserRepository implements IAnswerRepository{

    @Autowired
    MockedQuestionRepository mockedQuestionRepository;

    @Override
    public List<Answer> findByQuestion(Question question) {
        return mockedQuestionRepository.findById(question.getId())
                .map(Question::getAnswers)
                .orElse(List.of());
    }

    @Override
    public Optional<Answer> findById(Long id) {
        return mockedQuestionRepository.findAll().stream()
                .flatMap(q -> q.getAnswers().stream())
                .toList()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();
    }
}
