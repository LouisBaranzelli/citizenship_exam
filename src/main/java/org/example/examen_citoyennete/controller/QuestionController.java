package org.example.examen_citoyennete.controller;

import org.example.examen_citoyennete.controller.dto.QuestionDto;
import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/{language}/{theme}/{level}")
    public ResponseEntity<QuestionDto> getRandomQuestion(@PathVariable String theme, @PathVariable String level, @PathVariable String language){

        Theme themeEnum;
        Level levelEnum;
        Language languageEnum;

        try {
            themeEnum = Theme.valueOf(theme.toUpperCase());
            levelEnum = Level.valueOf(level.toUpperCase());
            languageEnum = Language.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
        Question question = null;
        try {
            question = questionService.getRandomQuestion(levelEnum, themeEnum, languageEnum);
            return ResponseEntity.ok(questionService.getDto(question, languageEnum));

        } catch (QuestionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{language}/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable String language, @PathVariable String id){
        Language languageEnum;
        Long idQuestion;
        try {
            languageEnum = Language.valueOf(language.toUpperCase());
            idQuestion = Long.valueOf(id);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
        Question question = null;
        try {
            question = questionService.getQuestion(idQuestion, languageEnum);
            return ResponseEntity.ok(questionService.getDto(question, languageEnum));

        } catch (QuestionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
