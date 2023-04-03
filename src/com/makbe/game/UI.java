package com.makbe.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.function.Function;

public class UI extends JFrame {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (screen.width / 2) + 80;
    int height = (screen.height / 2);

    Game game = new Game();

    JLabel correctLabel = new JLabel("Actual value: " + game.getActualValue());
    JLabel attemptsLabel = new JLabel("Attempts left: " + game.getAttempts());
    JTextField inputField = new JTextField();
    JButton enterButton = new JButton("Enter");
    JButton refreshButton = new JButton("Refresh");

    public UI() {
        super("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setUndecorated(true);

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        Font font = new Font("Choco cooky", Font.PLAIN, 20);

        JLabel label = new JLabel("Guess a number between " + game.getMin() + " and " + game.getMax());
        label.setBounds(0, 20, width, 40);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(font);
        add(label);

        inputField.setBounds(120, 80, 320, 40);
        inputField.setFont(font);
        add(inputField);

        enterButton.setBounds(450, 80, 150, 40);
        enterButton.setFont(font);
        enterButton.addActionListener(e -> {
            dataValidation(inputField.getText());
            inputField.setText("");
            attemptsLabel.setText("Attempts left: " + game.getAttempts());
            game.range();
            if (game.getAttempts() == 0)
                game.gameOver(this);
        });
        add(enterButton);

        attemptsLabel.setBounds(0, 150, width, 40);
        attemptsLabel.setHorizontalAlignment(JLabel.CENTER);
        attemptsLabel.setFont(font);
        add(attemptsLabel);

        correctLabel.setBounds(0, 200, width, 40);
        correctLabel.setHorizontalAlignment(JLabel.CENTER);
        correctLabel.setFont(font);
        correctLabel.setVisible(false);
        add(correctLabel);

        refreshButton.setBounds(200, 260, 150, 40);
        refreshButton.setFont(font);
        refreshButton.setEnabled(false);
        refreshButton.addActionListener(e -> game.refreshGame(this));
        add(refreshButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(380, 260, 150, 40);
        quitButton.setFont(font);
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);

        try {
            UIManager.setLookAndFeel(looks[3].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        setVisible(true);
    }

    private void dataValidation(String text) {
        int num = Integer.parseInt(text);
        if (num < game.getMin() || num > game.getMax())
            JOptionPane.showMessageDialog(this, "Please enter a number that is within the bounds!");
        else {
            game.setUserNumber(num);
            game.checkCorrect(this);
        }
    }

}
