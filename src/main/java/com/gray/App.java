package com.gray;

import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {

    private static Logger LOGGER = Logger.getLogger("Main");

    public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter expression: ");
            String expr = input.nextLine();
            LOGGER.info("User expression " + expr);
            ShuntingYard parser = new ShuntingYard();
            System.out.println(parser.toRpn(expr));
            Queue<String> x = parser.toRpn(expr);
            String[] rpnLine = new String[x.size()];
            System.out.println(parser.evalRpn(x.toArray(rpnLine)));
        }
    }
}