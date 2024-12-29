package com.mycompany.quizapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QuizApplication extends JFrame implements ActionListener {
    
    JLabel label;
    JRadioButton radioButtons[] = new JRadioButton[4];
    JButton btnNext, btnResult;
    ButtonGroup bg;
    int count = 0, current = 0;
    List<Question> questions;
    
    QuizApplication(String s) {
        super(s);
        label = new JLabel();
        add(label);
        bg = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            add(radioButtons[i]);
            bg.add(radioButtons[i]);
        }

        btnNext = new JButton("Next");
        btnResult = new JButton("Result");
        btnResult.setVisible(false);

        btnResult.addActionListener(this);
        btnNext.addActionListener(this);

        add(btnNext);
        add(btnResult);

        label.setBounds(30, 40, 450, 20);
        for (int i = 0; i < 4; i++) {
            radioButtons[i].setBounds(50, 80 + (i * 30), 450, 20);
        }
        btnNext.setBounds(100, 240, 100, 30);
        btnResult.setBounds(270, 240, 100, 30);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

        loadQuestions();
        setQuestion();
    }

    void loadQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Who wrote \"Romeo and Juliet\"?", new String[]{"Mark Twain", "Jane Austen", "Charles Dickens", "William Shakespeare"}, 3));
        questions.add(new Question("What is the hardest natural substance on Earth?", new String[]{"Ruby", "Diamond", "Iron", "Gold"}, 1));
        questions.add(new Question("What is the capital city of Australia?", new String[]{"Brisbane", "Canberra", "Melbourne", "Sydney"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Saturn", "Jupiter", "Mars", "Venus"}, 2));
        questions.add(new Question("What is the largest ocean on Earth?", new String[]{"Pacific Ocean", "Arctic Ocean", "Indian Ocean", "Atlantic Ocean"}, 0));
        questions.add(new Question("Who painted the Mona Lisa?", new String[]{"Claude Monet", "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh"}, 1));
        questions.add(new Question("In which year did the Titanic sink?", new String[]{"1945", "1920", "1912", "1905"}, 2));
        questions.add(new Question("What is the smallest country in the world by land area?", new String[]{"Vatican City", "San Marino", "Nauru", "Monaco"}, 0));
        questions.add(new Question("Which element has the chemical symbol 'O'?", new String[]{"Iron", "Silver", "Gold", "Oxygen"}, 3));
        questions.add(new Question("Who was the first President of the United States?", new String[]{"Thomas Jefferson", "John Adams", "Benjamin Franklin", "George Washington"}, 3));
    }

    void setQuestion() {
        if (current < questions.size()) {
            Question q = questions.get(current);
            label.setText(q.question);
            for (int i = 0; i < 4; i++) {
                radioButtons[i].setText(q.options[i]);
                radioButtons[i].setSelected(false);
            }
        }
    }

    boolean checkAnswer() {
        return radioButtons[questions.get(current).correctIndex].isSelected();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            if (checkAnswer()) count++;
            current++;
            if (current == questions.size() - 1) {
                btnNext.setEnabled(false);
                btnResult.setVisible(true);
            }
            setQuestion();
        } else if (e.getSource() == btnResult) {
            if (checkAnswer()) count++;
            JOptionPane.showMessageDialog(this, "Your Score is: " + count + "/" + questions.size());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new QuizApplication("Simple Quiz Application");
    }
}

class Question {
    String question;
    String[] options;
    int correctIndex;

    Question(String question, String[] options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }
}
