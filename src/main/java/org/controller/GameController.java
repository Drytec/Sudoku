package org.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.Random;

public class GameController {
    @FXML
    private GridPane gridSudoku;

    public void initialize() {
        setSudoku();
    }


    private TextField[][] textFields = new TextField[9][9]; // Matriz para almacenar los TextField
    private Integer row;
    @FXML
    private Label textValide;
    private Integer col;

    public boolean comprobarNumero(String character,int row, int col) {
        String valorTextField = getTextFieldValue(row, col);

        int startRow = row - row % 3;
        int startCol = col - col % 3;


        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if ((i != row || j != col) && getTextFieldValue(i, j).equals(valorTextField)) {
                    return true;
                }
            }}
        for (int i = 0; i < 8; i++) {
            String valorTextField1 = getTextFieldValue(i, col);//

            if (valorTextField.equals(valorTextField1) && i != row) {
                return true;
            }
        }
        for (int j = 0; j < 8; j++) {
            String valorTextField1 = getTextFieldValue(row, j);
            if (valorTextField.equals(valorTextField1) && j != col) {
                return true;
            }
        }
        for (int i = col; i < 8; i++) {
            for (int j = row; j < 8; j++) {

            }

        }



        return false;
    }
    public void setSudoku() {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };


        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                int decidir = random.nextInt(3);
                if (decidir != 1) {
                    textField.setText("");
                } else {
                    textField.setText(String.valueOf(sudoku[j][i]));
                }
                Font font = Font.font("Bernard MT Condensed", 18);
                textField.setFont(font);
                textField.setAlignment(Pos.CENTER);
                textField.setPrefWidth(35);
                textField.setPrefHeight(35);

                final int row = i;
                final int col = j;

                textField.setOnKeyTyped(event -> {

                    boolean esValido = comprobarNumero(event.getCharacter(), row, col);

                    if (!esValido) {
                        // Si el número es válido, oculta el Label
                        textValide.setVisible(false);

                    }
                    else if(textField.getText().isEmpty()){
                        textValide.setVisible(false);
                    }
                    else {
                        // Si el número no es válido, muestra el Label y borra el texto del TextField
                        textField.clear();
                        textValide.setVisible(true);
                    }
                });

                gridSudoku.add(textField, i, j);
                textFields[i][j] = textField;
            }
        }
    }


    public String getTextFieldValue(int col, int row) {
        return textFields[col][row].getText();
    }


}

