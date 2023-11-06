package giza.example.springbootessentials.Exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student does not exist");
    }
}
