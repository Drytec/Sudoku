package org.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameController {
    @FXML
    private GridPane gridSudoku; //Grid where the text fields will be added

    private static TextField[][] textFields = new TextField[9][9]; // Matrix to store each TextField
    @FXML
    private Label textNotValide;

    @FXML
    private Label textValid;

    public void initialize() { //Function that initializes the main methods of the game
        setSudoku();
        constantSudokufunction();
    }

    public boolean verifyNumber(String character, int row, int col) {
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

        return false;
    }

    public static List<Integer> generateUniqueRandomNumbers(int count) {
        if (count > 9) {
            throw new IllegalArgumentException("Cannot generate more unique numbers than the range allows.");
        }

        List<Integer> numbers = new ArrayList<>(); //A list is created to store the random numbers
        Random random = new Random();

        while (numbers.size() < count) {
            int randomNumber = random.nextInt(9); // Generates a random number between 0 and 8
            if (!numbers.contains(randomNumber)) { //If the number isn't already on the list, it will be added
                numbers.add(randomNumber);
            }
        }

        // Sort the list in ascending order
        Collections.sort(numbers);

        return numbers;
    }
    public void showPartialSolution(GridPane gridPane, int[][] sudokuSolution) {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {  //These nested loops mark the initial index of each 3x3 grid
                List<Integer> randomNumbers = generateUniqueRandomNumbers(4);
                int count1 = 0; //Counts until 9, the size of the loop
                int count2 = 0; //Counts to 4, the size of the list of random numbers
                for (int i = row; i < row + 3; i++) {
                    for (int j = col; j < col + 3; j++) {  //The nested loops move through the 3x3 grids
                        TextField textField = new TextField();
                        Font font = Font.font("Bernard MT Condensed", 18);
                        textField.setFont(font);
                        textField.setAlignment(Pos.CENTER);
                        textField.setPrefWidth(35);
                        textField.setPrefHeight(35);
                        gridPane.add(textField, j, i);
                        if(count2 < 4) {
                            if (count1 == randomNumbers.get(count2)) { //If the initial count is the same as the number at the index of the second count,
                                count1++;                               //the number of the sudoku solution at the current index will be added to the gridPane.
                                count2++;                               //The count is until 4 because there has to be only 4 visible numbers per grid.
                                textField.setText(String.valueOf(sudokuSolution[i][j]));
                                textField.setEditable(false);
                                textFields[i][j] = textField;
                            } else {                        //If the counts aren't equal, an editable space will be left in the grid.
                                textField.setText("");
                                textField.setEditable(true);
                                count1++;
                                textFields[i][j] = textField;
                            }
                        } else {                        //If the second count is over 4, the remaining text fields will be left as editable spaces.
                            textField.setText("");
                            textField.setEditable(true);
                            count1++;
                            textFields[i][j] = textField;
                        }
                    }
                }
            }
        }
    }

    public void setSudoku() {
        List<int[][]> listOfSudokuSolutions = new ArrayList<>(); //List to store the sudoku solutions
        int[][] option1 = { //4 sudoku solutions are created and added to the list
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
        listOfSudokuSolutions.add(option1);
        int[][] option2 = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 1, 4, 3, 6, 5, 8, 9, 7},
                {3, 6, 5, 8, 9, 7, 2, 1, 4},
                {8, 9, 7, 2, 1, 4, 3, 6, 5},
                {5, 3, 1, 6, 4, 2, 9, 7, 8},
                {6, 4, 2, 9, 7, 8, 5, 3, 1},
                {9, 7, 8, 5, 3, 1, 6, 4, 2}
        };
        listOfSudokuSolutions.add(option2);
        int[][] option3 = {
                {9, 8, 7, 6, 5, 4, 3, 2, 1},
                {6, 5, 4, 3, 2, 1, 9, 8, 7},
                {3, 2, 1, 9, 8, 7, 6, 5, 4},
                {8, 7, 6, 5, 4, 3, 2, 1, 9},
                {5, 4, 3, 2, 1, 9, 8, 7, 6},
                {2, 1, 9, 8, 7, 6, 5, 4, 3},
                {7, 6, 5, 4, 3, 2, 1, 9, 8},
                {4, 3, 2, 1, 9, 8, 7, 6, 5},
                {1, 9, 8, 7, 6, 5, 4, 3, 2}
        };
        listOfSudokuSolutions.add(option3);
        int[][] option4 = {
                {1, 5, 2, 4, 8, 9, 3, 7, 6},
                {7, 3, 9, 2, 5, 6, 8, 4, 1},
                {4, 6, 8, 3, 7, 1, 2, 9, 5},
                {3, 8, 7, 1, 2, 4, 6, 5, 9},
                {5, 9, 1, 7, 6, 3, 4, 2, 8},
                {2, 4, 6, 8, 9, 5, 7, 1, 3},
                {9, 1, 4, 6, 3, 7, 5, 8, 2},
                {6, 2, 5, 9, 4, 8, 1, 3, 7},
                {8, 7, 3, 5, 1, 2, 9, 6, 4}
        };
        listOfSudokuSolutions.add(option4);
        Random random = new Random();
        int randomSolution = random.nextInt(4);
                                                        //A random solution is chosen for the game
        showPartialSolution(gridSudoku, listOfSudokuSolutions.get(randomSolution));
    }

    public void constantSudokufunction(){ //Function to verify the numbers entered and deleted through Key events
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TextField textField = getTextFieldAt(row, col);
                int finalRow = row;
                int finalCol = col;
                textField.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        // Perform validation when ENTER key is pressed
                        boolean isValid = verifyNumber(textField.getText(), finalRow, finalCol);
                        if (!isValid) {
                            // Number is valid
                            textValid.setVisible(true);
                            textNotValide.setVisible(false);
                            textFields[finalRow][finalCol].setText(String.valueOf(textField.getText()));
                        } else {
                            // Number is not valid
                            textValid.setVisible(false);
                            textNotValide.setVisible(true);
                        }
                    }
                    if (event.getCode() == KeyCode.DELETE){
                        textFields[finalRow][finalCol].setText("");
                    }
                });
            }
        }
    }

    private TextField getTextFieldAt(int row, int col) { //Method that returns a text field at a specific index
        return textFields[row][col];
    }
    public String getTextFieldValue(int row, int col) { // Returns the text of a text field at a specific index
        TextField textField = getTextFieldAt(row, col);
        return textField != null ? textField.getText() : ""; // Return empty string if no TextField found
    }


}
