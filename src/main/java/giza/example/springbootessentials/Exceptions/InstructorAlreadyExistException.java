package giza.example.springbootessentials.Exceptions;

public class InstructorAlreadyExistException extends RuntimeException{

    public InstructorAlreadyExistException(){
        super("Instructor with Email or PhoneNumber already exists");
    }
}
