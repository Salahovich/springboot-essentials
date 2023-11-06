package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.CourseNotFoundException;
import giza.example.springbootessentials.Exceptions.StudentAlreadyEnrolled;
import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.CourseRepositoryJpa;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import giza.example.springbootessentials.Validations.CourseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepositoryJpa repository;

    @Autowired
    private StudentRepositoryJpa studentRepository;

    @Autowired
    private CourseValidation validation;

    public Iterable<Course> findAll(){
        return repository.findAll();
    }

    public Course findCourseById(UUID uuid){
        return repository.findById(uuid).orElseThrow(CourseNotFoundException::new);
    }
    public List<Map<String, Object>> findCourseAndStudentsEnrolledIn(){
        return repository.findCoursesAndStudentsEnrolledIn();
    }

    public Course saveCourse(Course course){
        course.setName(course.getName().toLowerCase());
        checkName(course.getName());

        return repository.save(course);
    }
    public Course addStudent(UUID studentId, UUID courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = repository.findById(courseId).orElseThrow(CourseNotFoundException::new);
        course.getStudents().stream().peek(s-> {
            if(s.getId() == studentId)
                throw new StudentAlreadyEnrolled();
        });

        course.getStudents().add(student);
        return repository.save(course);
    }
    public Course updateCourse(Course course){
        return null;
    }
    public void deleteCourseById(UUID id){
        findCourseById(id);
        repository.deleteById(id);
    }

    // validation methods
    protected void checkName(String name){
        validation.checkName(name);
    }
}
