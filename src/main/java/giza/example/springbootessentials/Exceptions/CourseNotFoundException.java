package giza.example.springbootessentials.Exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(){
        super("Course is not found exception");
    }
}
