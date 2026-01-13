package org.example.examen_citoyennete.controller;

public class QuestionNotFoundException extends Exception{

    public QuestionNotFoundException() {
        super();
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
