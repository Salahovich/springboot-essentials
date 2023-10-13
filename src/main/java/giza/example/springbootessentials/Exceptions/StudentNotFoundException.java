package giza.example.springbootessentials.Exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("No User found exception");
    }
}
