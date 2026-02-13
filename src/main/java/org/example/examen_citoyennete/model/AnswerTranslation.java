package org.example.examen_citoyennete.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class AnswerTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("labelAnswer")
    private String labelAnswer;

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerTranslation(Language language, Answer answer, String labelAnswer, Long id){
        this.language = language;
        this.answer = answer;
        this.labelAnswer = labelAnswer;
        this.id = id;
    }

    public AnswerTranslation(){
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

    public String getLabelAnswer() {
        return labelAnswer;
    }

    public Answer answer() {
        return answer;
    }
}
