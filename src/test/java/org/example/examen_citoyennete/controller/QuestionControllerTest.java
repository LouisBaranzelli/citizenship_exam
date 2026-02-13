package org.example.examen_citoyennete.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.examen_citoyennete.controller.dto.QuestionDto;
import org.example.examen_citoyennete.model.Answer;
import org.example.examen_citoyennete.model.AnswerTranslation;
import org.example.examen_citoyennete.model.Language;
import org.example.examen_citoyennete.repository.question.MockedQuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MockedQuestionRepository mockedQuestionRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testGetRandomQuestion() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/questions/FR/T1/L1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]") )
                .andExpect(status().isOk()).andReturn();
        String jsonAnswer = mvcResult.getResponse().getContentAsString();
        QuestionDto questionDto = mapper.readValue(jsonAnswer, QuestionDto.class);
        assertNotEquals(null, questionDto);
        assertEquals(2, questionDto.getAnswers().size());

        mockMvc.perform(MockMvcRequestBuilders.post("/questions/EN/T1/L1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]") )
                .andExpect(status().isNotFound());

        Answer missinAnswerTranslation = mockedQuestionRepository.findById(questionDto.getId()).get().getAnswers().stream().filter(a -> a.getAnswersTranslations().size() == 1).findFirst().orElse(null);
        missinAnswerTranslation.getAnswersTranslations().add(new AnswerTranslation(Language.EN, missinAnswerTranslation, "I am 30", 10L));
        mockMvc.perform(MockMvcRequestBuilders.post("/questions/EN/T1/L1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]") )
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/questions/FR/T1/L3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[]") )
                .andExpect(status().isNotFound());
    }

    @Test
    void getQuestion() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/questions/FR/0"))
                .andExpect(status().isOk()).andReturn();
        String jsonAnswer = mvcResult.getResponse().getContentAsString();
        QuestionDto questionDto = mapper.readValue(jsonAnswer, QuestionDto.class);
        assertNotEquals(null, questionDto);
        assertEquals(2, questionDto.getAnswers().size());


        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/questions/EN/0"))
                .andExpect(status().isOk()).andReturn();
        jsonAnswer = mvcResult.getResponse().getContentAsString();
        questionDto = mapper.readValue(jsonAnswer, QuestionDto.class);
        assertNotEquals(null, questionDto);
        assertEquals(2, questionDto.getAnswers().size());
    }
}
