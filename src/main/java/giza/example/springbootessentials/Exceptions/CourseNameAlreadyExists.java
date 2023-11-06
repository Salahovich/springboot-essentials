package giza.example.springbootessentials.Exceptions;

public class CourseNameAlreadyExists extends RuntimeException{

    public CourseNameAlreadyExists(){
        super("There's course with the same name exists");
    }
}
