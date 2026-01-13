package org.example.examen_citoyennete.model;

import jakarta.persistence.*;
import org.example.examen_citoyennete.controller.QuestionNotFoundException;
import org.example.examen_citoyennete.controller.dto.QuestionDto;

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

    public Question(){}

    public Question(Theme theme, Level level, Long id){
        this(theme, level);
        this.id = id;
    }


    public Question(Theme theme, Level level){
        this.theme = theme;
        this.level = level;
    }

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionTranslation> questionTranslations = new ArrayList<>();

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<QuestionTranslation> getQuestionTranslations() {
        return questionTranslations;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Theme getTheme() {
        return theme;
    }

    public Level getLevel() {
        return level;
    }
}
