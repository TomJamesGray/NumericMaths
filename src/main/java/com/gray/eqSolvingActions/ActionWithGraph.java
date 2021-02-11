package com.gray.eqSolvingActions;

import com.gray.RootFinding;
import com.gray.ShuntingYard;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.util.Queue;

public class ActionWithGraph {
    public LineChart<Number, Number> lineChart;

    public ActionWithGraph(VBox graphArea){
        NumberAxis xAxis = new NumberAxis(-5,5,1);
        xAxis.setLabel("x");

        NumberAxis yAxis = new NumberAxis(-5,5,1);
        yAxis.setLabel("y");

        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);

        LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        this.lineChart = lineChart;
        graphArea.getChildren().add(lineChart);
    }

    public String[] getRpnExpr(TextField functionField){
        ShuntingYard parser = new ShuntingYard();
        Queue<String> rpnQ = parser.toRpn(functionField.getText());
        return (rpnQ.toArray(new String[0]));
    }

    public void graphFunc(double lB, double uB, TextField functionField){
        lineChart.getData().clear();
        XYChart.Series series = new XYChart.Series();
        double lowerX = lB;
        double upperX = uB;
        final int points = 100;
        double stepSize = (upperX - lowerX) / points;

        ShuntingYard sy = new ShuntingYard();

        String[] rpn = getRpnExpr(functionField);
        for (double i = lowerX; i < upperX; i += stepSize){
            series.getData().add(new XYChart.Data(i, RootFinding.evalRpnWithX(rpn,i)));
        }
        lineChart.getData().add(series);

    }
}
