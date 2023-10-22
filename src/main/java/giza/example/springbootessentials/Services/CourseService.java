package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.CourseNotFoundException;
import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.CourseRepositoryJpa;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
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
        return repository.save(course);
    }
    public Course addStudent(UUID studentId, UUID courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        Course course = repository.findById(courseId).orElseThrow(CourseNotFoundException::new);
        course.getStudents().add(student);
        return repository.save(course);
    }
    public Course updateCourse(Course course){
        return null;
    }
    public void deleteCourseById(UUID id){
//        Course course = repository.save(findCourseById(id));
        findCourseById(id);
        repository.deleteById(id);
    }
}
