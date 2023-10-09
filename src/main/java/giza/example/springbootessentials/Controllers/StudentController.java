package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.Annotations.Traceable;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/university/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<List<Student>> findAll(){
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id){
        Student student = studentService.findStudentById(id);
        if(student == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }
    @PostMapping
    public ResponseEntity<List<Student>> addStudent(@RequestBody Student student){
        List<Student> students = studentService.addStudent(student);
        return ResponseEntity.ok(students);
    }
    @PutMapping
    public ResponseEntity<List<Student>> updateStudent(@RequestBody Student student){
        List<Student> students = studentService.updateStudent(student);
        if(students == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(students);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Student>> deleteStudent(@PathVariable int id){
        List<Student> students = studentService.deleteStudentById(id);
        return ResponseEntity.ok(students);
    }

}
