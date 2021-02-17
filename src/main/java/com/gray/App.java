package com.gray;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;

public class App extends Application{

    private static Logger LOGGER = Logger.getLogger("Main");

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setHeight(600);
        primaryStage.setWidth(900);
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/eqSolving.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}