package com.gray;

import com.gray.eqSolvingActions.bisection;
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
//            bisection
        }
    }
}
