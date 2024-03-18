package com.example.calculator_final;

import java.text.DecimalFormat;
import java.util.Stack;

public class Calculator {
    public String calculate(String toEval, boolean checker) {
        if (toEval == null || toEval.length() == 0) {
            return null;
        }

        Stack<Double> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < toEval.length(); i++) {
            char c = toEval.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < toEval.length() && (Character.isDigit(toEval.charAt(i)) || toEval.charAt(i) == '.')) {
                    sb.append(toEval.charAt(i));
                    i++;
                }
                i--; // sets index back to help prevent skipping operators
                nums.push(Double.parseDouble(sb.toString()));
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if(checker) { // A boolean to check if the calculation is sequential or following MDAS
                    while (!ops.isEmpty() && precedence(c, ops.peek())) {
                        nums.push(compute(ops.pop(), nums.pop(), nums.pop()));
                    }
                } else {
                    while (!ops.isEmpty()) {
                        nums.push(compute(ops.pop(), nums.pop(), nums.pop()));
                    }
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            nums.push(compute(ops.pop(), nums.pop(), nums.pop()));
        }

        double res = nums.pop();

        //Decimal Format Object for changing number of decimal points
        DecimalFormat decimalFormat = new DecimalFormat("#." + new String(new char[5]).replace('\0', '#'));
        String formattedValue = decimalFormat.format(res);

        return formattedValue;
    }

    private double compute(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    private boolean precedence(char op1, char op2) {
        return ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) ? false : true;
    }

}
