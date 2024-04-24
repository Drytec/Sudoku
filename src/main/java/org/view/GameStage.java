package org.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameStage extends Stage {
    public  GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sudoku/Sudoku-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setScene(scene);
        getIcons().add(new Image(String.valueOf(getClass().getResource("/org/example/sudoku/images/favicon.jpg"))));

        setResizable(false);
        show();

    }
    public static void getInstance() throws IOException {
        GameStageHolder.INTANCE = new GameStage();
    }
    private static class GameStageHolder{

        private static GameStage INTANCE;
    }
}