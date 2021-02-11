package com.gray;

import com.gray.eqSolvingActions.Bisection;
import com.gray.eqSolvingActions.Secant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class EqSolvingController {
    @FXML
    public ComboBox methodToUse;
    public VBox editableForAction;
    public VBox graphArea;
    public LineChart lineGraph;


    public void methodUpdate(ActionEvent actionEvent) {
        String method = (String) methodToUse.getSelectionModel().getSelectedItem();
        editableForAction.getChildren().clear();
        graphArea.getChildren().clear();

        if (method.equals("Bisection")){
            Bisection pane = new Bisection(editableForAction,graphArea);
        }
        if (method.equals("Secant")){
            Secant pane = new Secant(editableForAction,graphArea);
        }
    }
}