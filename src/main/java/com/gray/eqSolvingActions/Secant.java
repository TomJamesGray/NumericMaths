package com.gray.eqSolvingActions;

import com.gray.RootFinding;
import com.gray.ShuntingYard;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Queue;

public class Secant {
    private VBox outputBox;
    private TextField p2;
    private TextField p1;
    private TextField functionField;
    private Label answerText;

    public Secant(VBox box) {
        HBox line1 = new HBox();
        line1.getStyleClass().add("formLabel");
        line1.getChildren().add(new Label("Point 1"));
        this.p1 = new TextField();
        line1.getChildren().add(this.p1);

        HBox line2 = new HBox();
        line2.getStyleClass().add("formLabel");
        line2.getChildren().add(new Label("Point 2"));
        this.p2 = new TextField();
        line2.getChildren().add(this.p2);

        HBox line3 = new HBox();
        line3.getStyleClass().add("formLabel");
        line3.getChildren().add(new Label("f(x) = "));
        this.functionField = new TextField();
        line3.getChildren().add(this.functionField);

        HBox line4 = new HBox();
        Button solveBtn = new Button("Solve");
        solveBtn.setOnAction(value -> {
            this.runSecant();
        });
        line4.getChildren().add(solveBtn);

        HBox line5 = new HBox();
        Label answerText = new Label("");
        this.answerText = answerText;
        line5.getChildren().add(answerText);

        box.getChildren().addAll(line1, line2, line3, line4, line5);
        this.outputBox = box;
    }

    private boolean runSecant() {
        ShuntingYard parser = new ShuntingYard();
        Queue<String> rpnQ = parser.toRpn(functionField.getText());
        String[] rpn = rpnQ.toArray(new String[0]);
        double approxRoot;
        try {
            approxRoot = RootFinding.secantMethod(
                    Double.parseDouble(p1.getText()),
                    Double.parseDouble(p2.getText()),
                    rpn);
        } catch (ArithmeticException e) {
            this.answerText.setText("Error occurred: Function doesn't have opposite" +
                    " signs at upper and lower bounds. Can't bisect");
            return (false);
        }
        System.out.println(approxRoot);
        this.answerText.setText(String.format("Approximate root is x = %.4f", approxRoot));
        return (true);
    }
}
