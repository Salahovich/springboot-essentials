package giza.example.springbootessentials.Exceptions;

public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(){
        super("Student with the PhoneNumber or NationalId or Email already exists");
    }
}
