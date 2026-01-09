package org.example.examen_citoyennete.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    boolean isCorrect;

    public Answer() {}

   public Answer(Question question, boolean isCorrect){
       this.question = question;
       this.isCorrect = isCorrect;
   }

    @ManyToOne
    @JoinColumn(name = "question_id")
    Question question;

    @OneToMany(mappedBy = "answer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    List<AnswerTranslation> answersTranslations = new ArrayList<>();

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<AnswerTranslation> getAnswersTranslations() {
        return answersTranslations;
    }
}
