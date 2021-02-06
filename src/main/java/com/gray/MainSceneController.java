package com.gray;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;

public class MainSceneController {

    public VBox mainWindow;
    public VBox welcomeWindow;

    public void loadEquationSolving(ActionEvent actionEvent) throws IOException {
        mainWindow.getChildren().clear();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/eqSolving.fxml");
        loader.setLocation(xmlUrl);
        VBox eqSolvingWindow = loader.load();
        mainWindow.getChildren().add(eqSolvingWindow);
    }

    public void loadCalculations(ActionEvent actionEvent) {
    }
}
