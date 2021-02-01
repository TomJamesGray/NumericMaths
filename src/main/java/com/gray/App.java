package com.gray;

import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.logging.Logger;

public class App {

    private static Logger LOGGER = Logger.getLogger("Main");

    public static void main(String[] args) {
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
                RootFinding.bisect(lowerBnd, upperBnd,rpnLine);
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