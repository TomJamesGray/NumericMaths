package com.gray;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainSceneController {
    @FXML
    private VBox eqSolvingWindow;
    @FXML
    private VBox welcomeWindow;
    @FXML
    private VBox calculationsWindow;

    private VBox[] windows;

    @FXML private void initialize(){
        this.windows = new VBox[]{eqSolvingWindow, welcomeWindow, calculationsWindow};
        for (VBox window:windows){
            window.managedProperty().bind(window.visibleProperty());
        }
    }

    private void hideAllWindows(){
        for (VBox window : windows){
            window.setVisible(false);
        }
    }

    @FXML
    private void loadEquationSolving(){
        hideAllWindows();
        eqSolvingWindow.setVisible(true);
    }

    @FXML
    private void loadCalculations(){
        hideAllWindows();
        calculationsWindow.setVisible(true);
    }

}
