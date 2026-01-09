package org.example.examen_citoyennete.model;


import jakarta.persistence.*;

@Entity
public class QuestionTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String labelQuestion;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;


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
}
