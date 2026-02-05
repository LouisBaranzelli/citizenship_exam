package org.example.examen_citoyennete.controller.dto;

public class AnswerDto {

    private final Long id;
    private final String answer;
    private final String language;
    private final boolean isCorrect;

    public AnswerDto(Long id, String answer, String language, boolean isCorrect){
        this.id = id;
        this.answer = answer;
        this.language = language;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
