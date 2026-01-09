package org.example.examen_citoyennete.model;

import jakarta.persistence.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    Theme theme;
    Level level;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<QuestionTranslation> questionTranslations = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<AnswerTranslation> answerTranslations = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<QuestionTranslation> getQuestionTranslations() {
        return questionTranslations;
    }

    public List<AnswerTranslation> getAnswerTranslations() {
        return answerTranslations;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
