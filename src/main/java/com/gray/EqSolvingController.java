package com.gray;

import com.gray.eqSolvingActions.Bisection;
import com.gray.eqSolvingActions.Secant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class EqSolvingController {
    @FXML
    public ComboBox methodToUse;
    public VBox editableForAction;


    public void methodUpdate(ActionEvent actionEvent) {
        String method = (String) methodToUse.getSelectionModel().getSelectedItem();
        if (method.equals("Bisection")){
            Bisection pane = new Bisection(editableForAction);
        }
        if (method.equals("Secant")){
            Secant pane = new Secant(editableForAction);
        }
    }
}