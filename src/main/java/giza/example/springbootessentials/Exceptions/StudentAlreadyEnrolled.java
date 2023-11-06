package giza.example.springbootessentials.Exceptions;

public class StudentAlreadyEnrolled extends RuntimeException{
    public StudentAlreadyEnrolled(){
        super("The student already enrolled in this course");
    }
}
