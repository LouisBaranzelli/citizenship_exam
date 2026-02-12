package org.example.examen_citoyennete.repository;

import org.example.examen_citoyennete.model.Question;
import org.example.examen_citoyennete.repository.question.IQuestionRepository;
import org.example.examen_citoyennete.repository.question.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class QuestionDataLoader {

    public QuestionRepository questionRepository;

    Logger logger = LoggerFactory.getLogger(QuestionDataLoader.class);

    File folder = new File("C:\\Users\\baran\\Documents\\Louis - Synology\\Projet Programation\\database_citizenship_app");

    public QuestionDataLoader(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
        questionRepository.deleteAll();
        List<Question> questions = new ArrayList<>();
        for (File f : Objects.requireNonNull(getAllFiles(folder))) {
            if (f.isFile()){
                questions.addAll(new ArrayList<>(loadQuestions(f)));
            }
        }
        this.questionRepository.saveAll(questions);
    }


    private List<Question> loadQuestions(File csvFile){
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = List.of(mapper.readValue(csvFile, Question[].class));
        questions.forEach(q -> {
            q.getQuestionTranslations().forEach(t -> t.setQuestion(q));
            q.getAnswers().forEach(a -> {
                a.setQuestion(q);
                a.getAnswersTranslations().forEach(t -> t.setAnswer(a));
            });
        });
        return questions;

    }

    List<File> getAllFiles(File file){
        List<File> files = new ArrayList<>();
        for (File f : Objects.requireNonNull(file.listFiles())){
            if (f.isDirectory()){
                files.addAll(getAllFiles(f));
            } else {
                files.add(f);
            }
        }
        return files;
    }

}
