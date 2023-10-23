package giza.example.springbootessentials;

import giza.example.springbootessentials.Services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServicePositiveTests {

    @Test
    public void testSummingTwoPositiveIntegersAndReturnPositiveInteger(){
        int numberOne = 10;
        int numberTwo = 7;
        int actual = new CalculatorService().add(numberOne, numberTwo);
        int expected = numberOne + numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testSubtractingTwoPositiveIntegersAndReturnPositiveInteger(){
        int numberOne = 10;
        int numberTwo = 7;
        int actual = new CalculatorService().subtract(numberOne, numberTwo);
        int expected = numberOne - numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testMultiplyingTwoPositiveIntegersAndReturnPositiveInteger(){
        int numberOne = 10;
        int numberTwo = 7;
        int actual = new CalculatorService().multiply(numberOne, numberTwo);
        int expected = numberOne * numberTwo;
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testDividingTwoPositiveIntegersAndReturnPositiveInteger(){
        int numberOne = 35;
        int numberTwo = 7;
        int actual = new CalculatorService().divide(numberOne, numberTwo);
        int expected = numberOne / numberTwo;
        Assertions.assertEquals(expected, actual);
    }

}
