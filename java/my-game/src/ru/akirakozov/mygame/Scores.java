package ru.akirakozov.mygame;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author akirakozov
 */
public class Scores {
    private final Map<String, Integer> scores;

    public Scores(String[] commands) {
        scores = new HashMap<>();
        for (String command : commands) {
            scores.put(command, 0);
        }
    }

    public void addScores(String command, int points) {
        scores.put(command, scores.getOrDefault(command, 0) + points);
    }

    public void setScores(String command, int points) {
        scores.put(command, points);
    }

    public int getScores(String command) {
        return scores.getOrDefault(command, 0);
    }

    public Set<String> getCommandNames() {
        //Math.ma
        return scores.keySet();
    }
}
