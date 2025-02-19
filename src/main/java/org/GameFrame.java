package org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {

    private final int x_y_size = 3;
    private final int width = 600;
    private final int height = 600;
    volatile String nextValue = "X";

    private Cell[][] arena = new Cell[x_y_size][x_y_size];

    public GameFrame() throws HeadlessException {
        this.setBackground(Color.ORANGE);
        this.setResizable(true);
        this.setSize(300, 300);
        this.setBounds(0, 0, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(x_y_size, x_y_size));
        this.setTitle("Tic-Tac-Toe");


        for (int i = 0; i < x_y_size; i++) {
            for (int j = 0; j < x_y_size; j++) {
                Cell cell = getCell(i, j);
                arena[i][j] = cell;
                this.add(arena[i][j]);
            }
        }
        setVisible(true);
    }

    private Cell getCell(int i, int j) {
        Cell cell = new Cell();
        cell.setFont(new Font("Arial", Font.PLAIN, 40));
        cell.setBounds(i * (width / x_y_size), j * (height / x_y_size), width / x_y_size, height / x_y_size);
        cell.setFocusPainted(true);
        cell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("CLICK! ");
                if (cell.getText().isEmpty())
                    cell.setText(nextValue);
                if (nextValue.equals("X")) {
                    nextValue = "O";
                } else {
                    nextValue = "X";
                }
                checkArea();
            }
        });
        return cell;
    }

    private void checkArea() {
        for (int i = 0; i < x_y_size; i++) {
            if (checkRow(i) || checkLine(i) || checkSlantLeft() || checkSlantRight()) {
                JOptionPane.showMessageDialog(this, "WIN!");
                resetBoard();
                break;
            }
        }
        int countFill = 0;
        for (int i = 0; i < x_y_size; i++) {
            for (int j = 0; j < x_y_size; j++) {
                countFill = !arena[i][j].getText().isEmpty() ? countFill + 1 : countFill;
            }
        }
        if (countFill == x_y_size * x_y_size) {
            JOptionPane.showMessageDialog(this, "DRAW!");
            resetBoard();
        }
    }

    private boolean checkRow(int x) {
        for (int j = 1; j < x_y_size; j++) {
            if (!arena[x][j - 1].getText().equals(arena[x][j].getText()) || arena[x][j - 1].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkLine(int y) {
        for (int x = 1; x < x_y_size; x++) {
            if (!arena[x - 1][y].getText().equals(arena[x][y].getText()) || arena[x - 1][y].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSlantLeft() {
        for (int i = 1; i < x_y_size; i++) {
            if (!arena[i - 1][i - 1].getText().equals(arena[i][i].getText()) || arena[i - 1][i - 1].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkSlantRight() {
        for (int i = 0; i < x_y_size - 1; i++) {
            if (!arena[i][x_y_size - 1  - i].getText().equals(arena[i + 1][x_y_size - 1  - i - 1].getText()) || arena[i][x_y_size - 1 - i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < x_y_size; i++) {
            for (int j = 0; j < x_y_size; j++) {
                arena[i][j].setText("");
            }
        }
    }
}