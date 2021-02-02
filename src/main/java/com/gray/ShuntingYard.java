package com.gray;

import java.awt.desktop.SystemSleepEvent;
import java.util.*;
import java.lang.Math;
import java.util.logging.Logger;

public class ShuntingYard {
    private static Logger LOGGER = Logger.getLogger("ShuntingYard");
    private String regexTokenizerExpr;
    Map<String,ExprFunction> operators;
    Map<String,ExprFunction> unaryOpeartors;

    public ShuntingYard(){
        ExprFunction unaryAddition = new ExprFunction("\\+",1,5,(Double [] x) -> x[0]);
        ExprFunction unarySubtraction = new ExprFunction("-",1,5,(Double [] x) -> -x[0]);
        Map<String,ExprFunction> unaryOperators = new HashMap<String,ExprFunction>();
        unaryOperators.put("+",unaryAddition);
        unaryOperators.put("-",unarySubtraction);
        this.unaryOpeartors = unaryOperators;

        ExprFunction addition = new ExprFunction("\\+",2, 2, (Double [] x) -> x[0] + x[1]);
        ExprFunction subtraction = new ExprFunction("-",2,2,(Double [] x) -> x[0] - x[1]);
        ExprFunction multiplication = new ExprFunction("\\*",2,3,(Double [] x) -> x[0] * x[1]);
        ExprFunction division = new ExprFunction("\\/",2,3,(Double [] x) -> x[0] / x[1]);
        ExprFunction sine = new ExprFunction("sin",1,6,(Double [] x) -> Math.sin(x[0]));
        ExprFunction cos = new ExprFunction("cos",1,6,(Double [] x) -> Math.cos(x[0]));
        ExprFunction lBracket = new ExprFunction("\\(",0,1,null);
        ExprFunction rBracket = new ExprFunction("\\)",0,1,null);

        Map<String,ExprFunction> operators = new HashMap<String,ExprFunction>();
        operators.put("+",addition);
        operators.put("-",subtraction);
        operators.put("*",multiplication);
        operators.put("/",division);
        operators.put("sin",sine);
        operators.put("cos",cos);
        operators.put("(",lBracket);
        operators.put(")",rBracket);
        this.operators = operators;

        //Generate regex expression
        StringBuilder regexExprBuilder = new StringBuilder();
        String joiner = "";
        for (ExprFunction func : operators.values()){
            regexExprBuilder.append(joiner);
            joiner = "|";
            regexExprBuilder.append("((?<=" + func.regexName + ")|(?=" + func.regexName + "))");
        }
        this.regexTokenizerExpr = regexExprBuilder.toString();
//        LOGGER.info("Regex splitting expression " + this.regexTokenizerExpr);
    }

    public Queue<String> toRpn(String expr){
        // Initialise stacks and queues for shunting yard algorithm
        Deque<String> funcStack = new ArrayDeque<String>();
        Queue<String> outputQueue = new LinkedList<String>();
        String lastChar = null;
        String[] tokens = expr.split(this.regexTokenizerExpr);
        int i = 0;
        while (i < tokens.length){
            String token = tokens[i];
            if (isUnaryFunc(token,lastChar)){
                System.out.println(token + " is unary function");
//                Translate the unary function to a binary function so -3 becomes 0 - 3 or 0 3 - in RPN
                outputQueue.add("0");
                outputQueue.add(tokens[i+1]);
                lastChar = tokens[i+1];
                outputQueue.add(token);
                i += 1;
            }
            else if (isFunc(token)){
                System.out.println(token + " is  function");
                if (token.equals("(")){
                    funcStack.push("(");
                    lastChar = "(";
                }
                else if (token.equals(")")) {
//                    Pop off functions until we reach the opening bracket
                    while (!funcStack.peek().equals("(")){
                        outputQueue.add(funcStack.pop());
                    }
                    funcStack.pop();
                    lastChar = token;
                }
                else if (funcStack.size() == 0){
                    funcStack.push(token);
                    lastChar = token;
                }
                else if (operators.get(funcStack.peek()).level < operators.get(token).level){
                    funcStack.push(token);
                    lastChar = token;
                }
                else{
                    try {
                        while (operators.get(funcStack.peek()).level >= operators.get(token).level) {
                            outputQueue.add(funcStack.pop());
                        }
                    }
                    catch (java.lang.NullPointerException e){
//                        Function stack is empty, do nothing
                    }
                    funcStack.push(token);
                    lastChar = token;
                }

            }
            else{
                System.out.println(token + " is operand");
                outputQueue.add(token);
                lastChar = token;
            }
            i += 1;
        }
        while (funcStack.size() > 0){
            outputQueue.add(funcStack.pop());
        }
        return(outputQueue);
    }

    public double evalRpn(String[] line, Map<String,Double> vars){
        Deque<Double> evalStack = new ArrayDeque<>();
        for (String token : line){
            if (isFunc(token)){
                ExprFunction func = operators.get(token);
                int argNum = func.numOfArgs;
                Double args[] = new Double[argNum];
//                Retrieve required number of args
                for (int i = argNum - 1; i >= 0; i--){
                    args[i] = evalStack.pop();
                }
                Double val = func.operation.apply(args);
                evalStack.push(val);
            }
            else{
                if (vars != null){
                    if (vars.containsKey(token)) {
//                        System.out.println("Vars contains this key! value is " + vars.get(token));
                        evalStack.push(vars.get(token));
                    }
                    else{
                        evalStack.push(Double.parseDouble(token));
                    }
                }
                else {
                    evalStack.push(Double.parseDouble(token));
                }
            }
        }
        return evalStack.pop();
    }

    public boolean isFunc(String name){
        if (name == null){
            return(false);
        }
        for (String funcName : operators.keySet()){
            if (name.equals(funcName)){
                return(true);
            }
        }
        return(false);
    }

    public boolean isUnaryFunc(String name,String lastChar){
        if (isFunc(lastChar) || lastChar == null){
//            Verify func name is a valid unary operators
            for (String funcName : unaryOpeartors.keySet()){
                if (funcName.equals(name)){
                    return(true);
                }
            }
        }
        return(false);
    }
}