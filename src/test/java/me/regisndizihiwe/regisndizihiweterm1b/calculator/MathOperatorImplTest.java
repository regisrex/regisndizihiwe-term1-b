package me.regisndizihiwe.regisndizihiweterm1b.calculator;

import me.regisndizihiwe.regisndizihiweterm1b.exceptions.InvalidOperationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MathOperatorImplTest {

    private final MathOperatorImpl mathOperator;

    public MathOperatorImplTest() {
        this.mathOperator = new MathOperatorImpl();
    }

    @Test
    public void givenTwoOperands_whenAdded_returnsSum() throws InvalidOperationException {
        double actual = mathOperator.doMath(1, 2, "+");
        double expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTwoOperands_whenDivided_returnsQuotient() throws InvalidOperationException {
        double actual = mathOperator.doMath(4, 2, "/");
        double expected = 2;
        assertEquals(expected, actual);
    }
    @Test
    public void givenAnumber_whenDividedByZero_itThrowsException() throws InvalidOperationException {
       assertThrows(InvalidOperationException.class, () ->  mathOperator.doMath(34, 0, "/"));
    }

    @Test
    public void givenTwoOperands_whenSubstracted_returnsDifference() throws InvalidOperationException {
        double actual = mathOperator.doMath(10, 5, "-");
        double expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTwoOperands_whenMultiplied_returnsProduct() throws InvalidOperationException {
        double actual = mathOperator.doMath(10, 5, "*");
        double expected = 50;
        assertEquals(expected, actual);
    }



}
