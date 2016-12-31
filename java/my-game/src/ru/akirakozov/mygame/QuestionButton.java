package ru.akirakozov.mygame;

import javax.swing.*;

/**
 * @author akirakozov
 */
public class QuestionButton extends JButton {
    private final String theme;
    private final int numberInTheme;

    public QuestionButton(String theme, int numberInTheme) {
        super(Integer.toString(numberInTheme * 100));
        this.theme = theme;
        this.numberInTheme = numberInTheme;
    }


    public String getTheme() {
        return theme;
    }

    public int getNumberInTheme() {
        return numberInTheme;
    }

    public int getPoints() {
        return numberInTheme * 100;
    }

}
