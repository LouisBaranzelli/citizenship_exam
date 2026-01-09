package org.example.examen_citoyennete.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AnswerTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question question;

    public Long getId() {
        return id;
    }

    public void setQuestion(Question question) {

        this.question = question;
    }
}
