package giza.example.springbootessentials.Services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int add(int numberOne, int numberTwo){
        return numberOne + numberTwo;
    }

    public int subtract(int numberOne, int numberTwo){
        return numberOne - numberTwo;
    }

    public int multiply(int numberOne, int numberTwo){
        return numberOne * numberTwo;
    }

    public int divide(int numberOne, int numberTwo){
        return numberOne / numberTwo;
    }
}
