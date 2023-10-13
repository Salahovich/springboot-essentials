package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.CourseNotFoundException;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Repositories.JPA.CourseRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseService {

//    @Autowired
//    private CourseRepository repository;

    @Autowired
    private CourseRepositoryJpa repository;

    public Iterable<Course> findAll(){
        return repository.findAll();
    }

    public Course findCourseById(UUID uuid){
        return repository.findById(uuid).orElseThrow(CourseNotFoundException::new);
    }

    public Course saveCourse(Course course){
        return repository.save(course);
    }

    public Course updateCourse(Course course){
        return null;
    }

    public void deleteCourseById(UUID id){
        repository.deleteById(id);
    }
}
