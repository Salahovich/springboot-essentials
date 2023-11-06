package giza.example.springbootessentials.Exceptions;

public class PhoneNumberFormatException extends RuntimeException{

    public PhoneNumberFormatException(){
        super("The phone number is not in the right format.");
    }
}
