package org.example.examen_citoyennete.model;

import jakarta.persistence.*;

@Entity
public class AnswerTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Language language;

    public void setId(Long id) {
        this.id = id;
    }


    @ManyToOne
    @JoinColumn(name = "answer_id")
    Answer answer;

    public Long getId() {
        return id;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Language language() {
        return language;
    }
}
