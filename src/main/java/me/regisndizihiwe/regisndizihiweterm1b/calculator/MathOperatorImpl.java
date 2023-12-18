package me.regisndizihiwe.regisndizihiweterm1b.calculator;

import me.regisndizihiwe.regisndizihiweterm1b.calculator.interfaces.MathOperator;
import me.regisndizihiwe.regisndizihiweterm1b.exceptions.InvalidOperationException;
import org.springframework.stereotype.Service;


@Service
public class MathOperatorImpl implements MathOperator {

    @Override
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        if ("/".equals(operation) && operand2 == (double) 0) {
            throw new InvalidOperationException("Cannot divide by 0");
        }
        switch (operation) {
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            default:
                throw new RuntimeException("Unknown operation");
        }
    }
}
