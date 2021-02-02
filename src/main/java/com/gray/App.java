package com.gray;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Logger;

public class App extends Application{

    private static Logger LOGGER = Logger.getLogger("Main");

    @Override
    public void start(Stage stage){
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 250);

        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
        while(true) {
            String func = getInput("Enter function (eval/bisect): ");
            String expr = getInput("Enter expression: ");

            LOGGER.info("User expression " + expr);
            ShuntingYard parser = new ShuntingYard();
            System.out.println(parser.toRpn(expr));
            Queue<String> x = parser.toRpn(expr);
            String[] rpnLine = new String[x.size()];
            rpnLine = x.toArray(rpnLine);

            if (func.equals("eval")){
                System.out.println(parser.evalRpn(x.toArray(rpnLine),null));
            }
            else if (func.equals("bisect")){
                Double lowerBnd = Double.parseDouble(getInput("Enter lower bound: "));
                Double upperBnd = Double.parseDouble(getInput("Enter upper bound: "));
                Double approxRoot = RootFinding.bisect(lowerBnd, upperBnd,rpnLine);
                System.out.println("Approximate root is " + approxRoot);
            }


        }
    }
    public static String getInput(String prompt){
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        String val = input.nextLine();
        return(val);
    }
}