package giza.example.springbootessentials.Exceptions;

public class NationalIdFormatException extends RuntimeException{

    public NationalIdFormatException(){
        super("The National Id is not in the right format.");
    }
}
