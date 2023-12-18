package me.regisndizihiwe.regisndizihiweterm1b.calculator.interfaces;

import me.regisndizihiwe.regisndizihiweterm1b.exceptions.InvalidOperationException;

public interface MathOperator {
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException ;
}
