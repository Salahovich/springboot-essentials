package giza.example.springbootessentials.Unit.Calculator;

import giza.example.springbootessentials.Services.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorServiceHybridTests {

    @Test
    public void testSummingPositiveIntegerAndNegativeIntegersAndReturnInteger(){
        int numberOne = 10;
        int numberTwo = -7;
        int actual = new CalculatorService().add(numberOne, numberTwo);
        int expected = numberOne + numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testSubtractingPositiveIntegerAndNegativeIntegersAndReturnInteger(){
        int numberOne = 10;
        int numberTwo = -7;
        int actual = new CalculatorService().subtract(numberOne, numberTwo);
        int expected = numberOne - numberTwo;
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void testMultiplyingPositiveIntegerAndNegativeIntegersAndReturnNegativeInteger(){
        int numberOne = 10;
        int numberTwo = -7;
        int actual = new CalculatorService().multiply(numberOne, numberTwo);
        int expected = numberOne * numberTwo;
        Assertions.assertEquals(expected,actual);
    }
    @Test
    public void testDividingPositiveIntegerAndNegativeIntegersAndReturnNegativeInteger(){
        int numberOne = 35;
        int numberTwo = -7;
        int actual = new CalculatorService().divide(numberOne, numberTwo);
        int expected = numberOne / numberTwo;
        Assertions.assertEquals(expected, actual);
    }

}
