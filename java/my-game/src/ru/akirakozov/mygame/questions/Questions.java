package ru.akirakozov.mygame.questions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * @author akirakozov
 */
public class Questions {
    public final Map<String, List<String>> questions;

    @JsonCreator
    public Questions(@JsonProperty("questions") Map<String, List<String>> questions) {
        this.questions = questions;
    }
}
