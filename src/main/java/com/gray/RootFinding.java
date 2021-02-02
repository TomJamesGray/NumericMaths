package com.gray;

import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class RootFinding {
    public static double bisect(double a, double b, String[] rpnLine) throws ArithmeticException{
        Double fOfA = evalRpnWithX(rpnLine,a);
        Double fOfB = evalRpnWithX(rpnLine,b);
        if (! (fOfA * fOfB < 0)){
//            Values don't have opposite signs so exit
            throw new ArithmeticException("Function evaluated at upper and lower bounds" +
                    "does not have opposite signs");
        }
        Double p = (a+b)/2;
        Double fOfP = evalRpnWithX(rpnLine,p);
        final int MAX_BISECTIONS = 1000;
        final double EPSILON = 0.00000001;
        int iters = 0;
        while (iters < MAX_BISECTIONS && Math.abs(fOfP) > EPSILON){
            if (fOfP * fOfA > 0){
//                f(p) and f(a) have same signs
                a = p;
            }
            else{
                b = p;
            }
            p = (a+b)/2;
            fOfP = evalRpnWithX(rpnLine,p);
            fOfA = evalRpnWithX(rpnLine,a);
            iters += 1;
        }

        return(p);
    }
    private static double evalRpnWithX(String[] rpnLine, Double x){
        ShuntingYard sY = new ShuntingYard();
        return(sY.evalRpn(rpnLine, Collections.singletonMap("x",x)));
    }
}
