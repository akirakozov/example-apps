package ru.akirakozov.mygame.questions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Set;


/**
 * @author akirakozov
 */
public class QuestionsRepository {
    private final Questions questions;

    public QuestionsRepository() {
        try {
            File f = new File("questions.json");
            questions = createObjectMapper().readValue(new File("questions.json"), Questions.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String getQuestion(String theme, int num) {
        try {
            return questions.questions.get(theme).get(num);
        } catch (Exception e) {
            return "Unknown question!";
        }
    }

    public Set<String> getThemes() {
        return questions.questions.keySet();
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return mapper;
    }
}
