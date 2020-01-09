package ru.akirakozov.mygame;

import ru.akirakozov.mygame.questions.QuestionsRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private final static QuestionsRepository questionRepo = new QuestionsRepository();
    private final static Scores scores = new Scores(new String[]{"Антон&Саша", "Юля&Настя"});
    private final static Map<String, JLabel> scoreLabels = new HashMap<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Своя игра");
        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel questionsPanel = new JPanel();

        ArrayList<String> themes = new ArrayList<>(questionRepo.getThemes());
        questionsPanel.setLayout(new GridLayout(0, 6));
        for (String theme : themes) {
            JLabel themeLabel = new JLabel(theme);
            themeLabel.setFont(new Font(themeLabel.getFont().getName(), Font.PLAIN, 40));
            questionsPanel.add(themeLabel);
            for (int j = 1; j <= 5; j++) {
                JButton button = new QuestionButton(theme, j);
                questionsPanel.add(button);
                button.setFont(new Font(button.getFont().getName(), Font.PLAIN, 60));
                button.addActionListener(createActionListener(frame));
            }
        }

        JPanel scoresPanel = new JPanel();
        for (String command : scores.getCommandNames()) {
            JLabel label = new JLabel(getCommandScoreText(command));
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 24));
            scoresPanel.add(label);
            scoreLabels.put(command, label);

            JButton editScoreButton = new JButton("+/-");
            scoresPanel.add(editScoreButton);
            editScoreButton.addActionListener(createEditScoreListener(frame, command));
        }

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(questionsPanel);
        container.add(scoresPanel);

        frame.add(container);

        frame.pack();
        frame.setVisible(true);
    }

    private static ActionListener createEditScoreListener(JFrame frame, String command) {
        return e -> {
            String score = JOptionPane.showInputDialog(
                    frame,
                    command,
                    "Set scores for " + command,
                    JOptionPane.INFORMATION_MESSAGE
            );
            try {
                scores.setScores(command, Integer.parseInt(score));
                scoreLabels.get(command).setText(getCommandScoreText(command));
            } catch (Exception exc) {
                // ignore
            }
        };
    }

    private static ActionListener createActionListener(JFrame frame) {
        return e -> {
            QuestionButton source = (QuestionButton) e.getSource();
            createQuestionMessageBox(frame, source);
            source.setEnabled(false);
        };

    }

    private static void createQuestionMessageBox(JFrame frame, QuestionButton qButton) {
        String[] options = scores.getCommandNames().toArray(new String[]{});
        String question = questionRepo.getQuestion(qButton.getTheme(), qButton.getNumberInTheme() - 1);
        JLabel label = new JLabel(prepareQuestionText(question));
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 30));
        //label.setMaximumSize(new Dimension(100, 50));
        int value = JOptionPane.showOptionDialog(
                frame,
                label,
                "Своя игра",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (0 <= value && value < options.length) {
            String command = options[value];
            scores.addScores(options[value], qButton.getPoints());
            scoreLabels.get(command).setText(getCommandScoreText(command));
        }
    }

    private static String prepareQuestionText(String question) {
        int maxLenght = 60;
        String[] parts = question.split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");

        int curlen = 0;
        for (String part : parts) {
            sb.append(part);
            sb.append(" ");
            curlen += part.length() + 1;
            if (curlen > maxLenght) {
                sb.append("<br>");
                curlen = 0;
            }
        }
        sb.append("</html>");
        return sb.toString();
    }

    private static String getCommandScoreText(String command) {
        return command + " : " + scores.getScores(command);
    }
}
