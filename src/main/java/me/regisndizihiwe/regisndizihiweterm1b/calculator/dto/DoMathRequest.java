package me.regisndizihiwe.regisndizihiweterm1b.calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoMathRequest {
    private double operand1;
    private double operand2;
    private String operation;
}
