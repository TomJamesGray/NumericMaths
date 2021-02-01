package com.gray;

import java.util.function.Function;

public class RootFinding {
    public static double bisect(double lowerBnd, double upperBnd, Function<Double,Double> func){
        System.out.println(func.apply(lowerBnd));
        System.out.println(func.apply(upperBnd));
        return(2.0);
    }
}
