package org.example.examen_citoyennete.model;


import jakarta.persistence.*;
import org.example.examen_citoyennete.controller.dto.QuestionDto;

@Entity
public class QuestionTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String labelQuestion;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    private Language language;

    public QuestionTranslation(Language language, String labelQuestion, Long id, Question question){
        this.language = language;
        this.labelQuestion = labelQuestion;
        this.id = id;
        this.question = question;
    }

    public QuestionTranslation(){};


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLabelQuestion() {
        return labelQuestion;
    }

    public void setLabelQuestion(String labelQuestion) {
        this.labelQuestion = labelQuestion;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Language language() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}
