package org.example.examen_citoyennete.controller.dto;

public class AnswerDto {

    private final Long id;
    private final String answer;
    private final String language;

    public AnswerDto(Long id, String answer, String language){
        this.id = id;
        this.answer = answer;
        this.language = language;
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
}
