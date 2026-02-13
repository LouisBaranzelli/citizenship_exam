package org.example.examen_citoyennete.service;

import org.example.examen_citoyennete.controller.QuestionNotFoundException;
import org.example.examen_citoyennete.controller.dto.AnswerDto;
import org.example.examen_citoyennete.controller.dto.QuestionDto;
import org.example.examen_citoyennete.model.*;
import org.example.examen_citoyennete.repository.answer.IAnswerRepository;
import org.example.examen_citoyennete.repository.answertranslation.IAnswerTranslation;
import org.example.examen_citoyennete.repository.question.IQuestionRepository;
import org.example.examen_citoyennete.repository.questionstranslation.IQuestionTranslationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@Service
public class QuestionService {

    private static Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final IQuestionRepository questionRepository;

    private final IAnswerRepository answerRepository;

    private final IQuestionTranslationRepository questionTranslationRepository;

    private final IAnswerTranslation answerTranslationRepository;

    HashMap<Long, String> mapPathImages = new HashMap<>();
    HashMap<Theme, List<String>> sentImage = new HashMap<>(); // t/path



    public QuestionService(IQuestionRepository questionRepository, IQuestionTranslationRepository questionTranslationRepository, IAnswerRepository answerRepository, IAnswerTranslation answerTranslationRepository){
        this.questionRepository = questionRepository;
        this.questionTranslationRepository = questionTranslationRepository;
        this.answerRepository = answerRepository;
        this.answerTranslationRepository = answerTranslationRepository;
    }

    public Question getQuestion(Long id, Language language) throws QuestionNotFoundException {
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            logger.warn("not available question for id: {}", id);
            throw new QuestionNotFoundException("ID not found: " + id);
        }

        if (!hasTranslation(question, language)){
            logger.warn("not available language ({}) question for id: {}",language, id);
            throw new QuestionNotFoundException("language " + language + " not found for id: " + id);
        }
        return question;
    }

    public Question getRandomQuestion(Level level, Theme theme, Language language, List<Long> historicId) throws QuestionNotFoundException {
       /*
       Retourne d'une Question et non d'une QuestionTranslation pour avoir aussi les answers
        */

        List<Question> questions = questionRepository.findByThemeAndLevel(theme, level).orElse(Collections.emptyList());
       if (questions.isEmpty()){
           logger.warn("not available questions for {}/{}", theme,level);
           throw new QuestionNotFoundException("not available questions");
       }
        Collections.shuffle(questions);

       Question output = questions.stream().filter(question -> {
           return hasTranslation(question, language) && !historicId.contains(question.getId());
        }).findFirst().orElse(null);

        if (output == null) {
            logger.warn("not available question ({}/{}) for language {}", theme, level, language);
            throw new QuestionNotFoundException("language not found");
        }

        return output;
    }

    public boolean hasTranslation(Question question, Language language)  {

        if (question == null || language == null){
            logger.error("question or language null");
            return false;
        }

        QuestionTranslation questionTranslation = question.getQuestionTranslations().stream()
                .filter(q -> q.language().equals(language))
                .findFirst().orElse(null);
        boolean hasTranslationQuestion = (questionTranslation != null);
        if (!hasTranslationQuestion){
            return false;
        }
        List<Answer> answers  = question.getAnswers();
        return answers.stream().allMatch(a -> {
           AnswerTranslation t = a.getAnswersTranslations().stream()
                   .filter(l -> l.language().equals(language))
                   .findFirst().orElse(null);
            return t != null;
        });
    }

    public QuestionDto getDto(Question question, Language language){
        List<Answer> answers = answerRepository.findByQuestion(question);
        QuestionTranslation questionTranslation = question.getQuestionTranslations().stream()
                .filter(q -> q.language().equals(language))
                .findFirst().orElseThrow();

        List<AnswerTranslation> answerTranslations = answers.stream()
                .map(a -> a.getAnswersTranslations().stream()
                        .filter(t -> t.language().equals(language))
                        .findFirst()
                        .orElseThrow()
                )
                .toList();

        List<AnswerDto> answersDto = answerTranslations.stream().map(answerTranslation ->
                new AnswerDto(answerTranslation.getId(), answerTranslation.getLabelAnswer(), language.name(), answerTranslation.answer().isCorrect())).toList();

        if (mapPathImages.get(question.getId()) == null){
            mapPathImages.put(question.getId(), getRandomPathThemeImages(question.getTheme()));
        }
        String pathImage = mapPathImages.get(question.getId());

        return new QuestionDto(question.getId(), questionTranslation.getLabelQuestion(), question.getTheme().name(), question.getLevel().name(), questionTranslation.language().name(), answersDto, pathImage);
    }


    public String getRandomPathThemeImages(Theme theme) {
        File root = new File("src/main/resources/static/background-question/" + theme);
        List<String> imagesAlreadySent = sentImage.computeIfAbsent(theme, t -> new ArrayList<>());

        File[] filesArray = root.listFiles(File::isFile);
        if (filesArray == null || filesArray.length == 0) {
            throw new RuntimeException("Aucune image trouvée pour le thème " + theme);
        }

        List<File> availableFiles = Arrays.stream(filesArray)
                .filter(f -> !imagesAlreadySent.contains("/background-question/" + theme + "/" + f.getName()))
                .collect(Collectors.toList());

        if (availableFiles.isEmpty()) {
            imagesAlreadySent.clear();
            availableFiles = Arrays.asList(filesArray);
        }

        File selectedFile = availableFiles.get(ThreadLocalRandom.current().nextInt(availableFiles.size()));
        String path = "/background-question/" + theme + "/" + selectedFile.getName();
        imagesAlreadySent.add(path);

        return path;
    }

}
