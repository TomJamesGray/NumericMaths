package com.gray.eqSolvingActions;

import com.gray.RootFinding;
import com.gray.ShuntingYard;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Bisection extends ActionWithGraph{
    private VBox outputBox;
    private TextField lowerBound;
    private TextField upperBound;
    public TextField functionField;
    private Label answerText;
    private VBox graphArea;
    private LineChart lineChart;

    public Bisection(VBox box, VBox graphArea){
        super(graphArea);
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

        HBox line3 = new HBox();
        line3.getStyleClass().add("formLabel");
        line3.getChildren().add(new Label("f(x) = "));
        this.functionField = new TextField();
        line3.getChildren().add(this.functionField);

        HBox line4 = new HBox();
        Button solveBtn = new Button("Solve");
        solveBtn.setOnAction(value -> {
            this.runBisect();
        });
        Button graphBtn = new Button("Graph");
        graphBtn.setOnAction(value ->{
            this.handleGraphFunc();
        });
        line4.getChildren().add(solveBtn);
        line4.getChildren().add(graphBtn);

        HBox line5 = new HBox();
        Label answerText = new Label("");
        this.answerText = answerText;
        line5.getChildren().add(answerText);

        box.getChildren().addAll(line1,line2,line3,line4,line5);
        this.outputBox = box;
    }

    private void handleGraphFunc(){
        System.out.println(functionField);
        graphFunc(Double.parseDouble(lowerBound.getText()),
                Double.parseDouble(upperBound.getText()),
                this.functionField);
    }

    private boolean runBisect() {
        String[] rpn = getRpnExpr(this.functionField);
        double approxRoot;
        try {
            approxRoot = RootFinding.bisect(
                    Double.parseDouble(lowerBound.getText()),
                    Double.parseDouble(upperBound.getText()),
                    rpn);
        }
        catch(ArithmeticException e){
            this.answerText.setText("Error occurred: Function doesn't have opposite" +
                    " signs at upper and lower bounds. Can't bisect");
            return(false);
        }
        System.out.println(approxRoot);
        this.answerText.setText(String.format("Approximate root is x = %.4f",approxRoot));
        return(true);
    }
}
