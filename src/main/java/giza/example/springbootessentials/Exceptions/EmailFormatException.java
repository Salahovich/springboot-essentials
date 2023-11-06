package giza.example.springbootessentials.Exceptions;

public class EmailFormatException extends RuntimeException{

    public EmailFormatException(){
        super("The email is in the wrong format.");
    }
}
