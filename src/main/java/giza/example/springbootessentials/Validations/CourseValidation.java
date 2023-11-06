package giza.example.springbootessentials.Validations;

import giza.example.springbootessentials.Exceptions.CourseNameAlreadyExists;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Repositories.Validations.CourseValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseValidation {

    @Autowired
    private CourseValidationRepository repository;

    public void checkName(String name){
        Optional<Course> course = repository.findCourseByName(name.toLowerCase());
        if(course.isPresent())
            throw new CourseNameAlreadyExists();
    }
}
