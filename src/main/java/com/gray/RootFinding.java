package com.gray;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class RootFinding {
    public static double bisect(double lowerBnd, double upperBnd, String[] rpnLine){
        System.out.println(evalRpnWithX(rpnLine,lowerBnd));
        System.out.println(evalRpnWithX(rpnLine,upperBnd));
        return(2.0);
    }
    private static double evalRpnWithX(String[] rpnLine, Double x){
        ShuntingYard sY = new ShuntingYard();
        return(sY.evalRpn(rpnLine, Collections.singletonMap("x",x)));
    }
}
