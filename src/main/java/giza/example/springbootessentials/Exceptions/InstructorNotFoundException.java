package giza.example.springbootessentials.Exceptions;

public class InstructorNotFoundException extends RuntimeException{

    public InstructorNotFoundException(){
        super("Instructor not found Exception");
    }
}
