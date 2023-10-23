package giza.example.springbootessentials;

import giza.example.springbootessentials.Services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceNegativeTests {
    @Test
    public void testSummingTwoNegativeIntegersAndReturnNegativeInteger(){
        int numberOne = -10;
        int numberTwo = -7;
        int actual = new CalculatorService().add(numberOne, numberTwo);
        int expected = numberOne + numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testSubtractingTwoNegativeIntegersAndReturnNegativeInteger(){
        int numberOne = -10;
        int numberTwo = -7;
        int actual = new CalculatorService().subtract(numberOne, numberTwo);
        int expected = numberOne - numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testMultiplyingTwoNegativeIntegersAndReturnPositiveInteger(){
        int numberOne = -10;
        int numberTwo = -7;
        int actual = new CalculatorService().multiply(numberOne, numberTwo);
        int expected = numberOne * numberTwo;
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testDividingTwoNegativeIntegersAndReturnPositiveInteger(){
        int numberOne = -35;
        int numberTwo = -7;
        int actual = new CalculatorService().divide(numberOne, numberTwo);
        int expected = numberOne / numberTwo;
        Assertions.assertEquals(expected, actual);
    }

}
