package com.gray.eqSolvingActions;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Bisection {
    private VBox outputBox;
    private TextField lowerBound;
    private TextField upperBound;
    public Bisection(VBox box){
        HBox line1 = new HBox();
        line1.getStyleClass().add("formLabel");
        line1.getChildren().add(new Label("Lower Bound"));
        this.lowerBound = new TextField();
        line1.getChildren().add(this.lowerBound);

        HBox line2 = new HBox();
        line2.getStyleClass().add("formLabel");
        line2.getChildren().add(new Label("Upper Bound"));
        this.upperBound = new TextField();
        line2.getChildren().add(this.upperBound);

        box.getChildren().addAll(line1,line2);
    }
}
