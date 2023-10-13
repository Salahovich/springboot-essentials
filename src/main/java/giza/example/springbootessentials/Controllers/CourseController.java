package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/university/courses")
public class CourseController {
    @Autowired
    private CourseService service;
    @GetMapping
    public ResponseEntity<Iterable<Course>> findAll(){
        Iterable<Course> courses = service.findAll();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable UUID id){
        Course course = service.findCourseById(id);
        return ResponseEntity.ok(course);
    }
    @PostMapping
    public ResponseEntity<Course> addCourse(@RequestBody @Valid Course course){
        course = service.saveCourse(course);
        return ResponseEntity.ok(course);
    }
    @PutMapping
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        course = service.updateCourse(course);
        return ResponseEntity.ok(course);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id){
        service.deleteCourseById(id);
        return ResponseEntity.ok("Course Deleted Successfully.");
    }
}
