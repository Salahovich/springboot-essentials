package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.DTO.CourseDto;
import giza.example.springbootessentials.EntityMappers.CourseMapper;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university/courses")
public class CourseController {
    @Autowired
    private CourseService service;
    @GetMapping
    public ResponseEntity<List<CourseDto>> findAll(){
        List<Course> courses = (List<Course>) service.findAll();
        return ResponseEntity.ok((courses.stream().map(c -> new CourseMapper().convertToDto(c)).collect(Collectors.toList())));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> findById(@PathVariable UUID id){
        Course course = service.findCourseById(id);
        return ResponseEntity.ok(new CourseMapper().convertToDto(course));
    }
    @GetMapping("/with-students")
    public ResponseEntity<List<Map<String, Object>>> findCoursesAndTheirStudents(){
        var coursesAndStudents = service.findCourseAndStudentsEnrolledIn();
        return ResponseEntity.ok(coursesAndStudents);
    }
    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody @Valid CourseDto courseDto){
        CourseMapper mapper = new CourseMapper();
        Course course = service.saveCourse(mapper.convertToEntity(courseDto));
        return ResponseEntity.ok(mapper.convertToDto(course));
    }

    @PutMapping("/add-student/{studentId}/{courseId}")
    public ResponseEntity<CourseDto> addStudent(@PathVariable UUID studentId, @PathVariable UUID courseId){
        return ResponseEntity.ok(new CourseMapper().convertToDto(service.addStudent(studentId, courseId)));
    }
    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        CourseMapper mapper = new CourseMapper();
        Course course = service.updateCourse(mapper.convertToEntity(courseDto));
        return ResponseEntity.ok(mapper.convertToDto(course));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id){
        service.deleteCourseById(id);
        return ResponseEntity.ok("Course Deleted Successfully.");
    }
}
