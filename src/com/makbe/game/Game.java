package com.makbe.game;

import javax.swing.*;
import java.util.Random;

public class Game {

    private final int actualValue;
    private int min;
    private int max;
    private int attempts = 3;
    int userNumber;

    public Game() {
        setMin(0);
        setMax(100);
        actualValue = generateRandom();
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getActualValue() {
        return actualValue;
    }

    public int getAttempts() {
        return attempts;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int generateRandom() {
        return new Random().nextInt(min, max);
    }

    public void checkCorrect(UI obj) {
        if (getActualValue() == userNumber) {
            JOptionPane.showMessageDialog(obj, "YOU WIN!");
            refreshGame(obj);
        }
        else {
            JOptionPane.showMessageDialog(obj, "YOU LOOSE!");
            attempts--;
        }
    }

    public void gameOver(UI obj) {
        obj.inputField.setEditable(false);
        obj.enterButton.setEnabled(false);
        obj.correctLabel.setText("Actual value: " + actualValue);
        obj.correctLabel.setVisible(true);
        obj.refreshButton.setEnabled(true);
    }

    public void refreshGame(UI obj) {
        attempts = 3;
        obj.game = new Game();
        obj.inputField.setEditable(true);
        obj.enterButton.setEnabled(true);
        obj.correctLabel.setVisible(false);
        obj.refreshButton.setEnabled(false);
        obj.attemptsLabel.setText("Attempts left: " + getAttempts());
    }

    public void range() {
        int difference = actualValue - userNumber;
        if (difference >= 30)
            System.out.println("Too low");
        else if (difference <= -30)
            System.out.println("Too High");
    }

}