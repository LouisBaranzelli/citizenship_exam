package org.example.examen_citoyennete.controller.dto;

import java.util.List;

public class QuestionDto {
    private final long id;
    private final String question;
    private final String theme;
    private final String level;
    private final String language;
    private final List<AnswerDto> answers;


    public QuestionDto(long id, String question, String theme, String level, String language, List<AnswerDto> answers) {
        this.id = id;
        this.question = question;
        this.theme = theme;
        this.level = level;
        this.language = language;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public String getQuestion() {
        return question;
    }

    public String getLevel() {
        return level;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public String getLanguage() {
        return language;
    }
}
