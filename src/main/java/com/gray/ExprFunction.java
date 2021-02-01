package com.gray;

import java.util.function.Function;

public class ExprFunction {
    String regexName;
    int numOfArgs;
    int level;
    Function<Double [],Double> operation;

    public ExprFunction(String fnName,int numOfArgs,int level, Function<Double [],Double> operator){
        this.regexName = fnName;
        this.numOfArgs = numOfArgs;
        this.level = level;
        this.operation = operator;
    }

    public Double doWith(Double [] x){
        return operation.apply(x);
    }
}