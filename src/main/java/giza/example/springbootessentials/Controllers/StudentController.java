package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/university/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Iterable<Student>> findAll(){
        Iterable<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable UUID id){
        Student student = studentService.findStudentById(id);
        if(student == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(student);
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student){
        student = studentService.saveStudent(student);
        return ResponseEntity.ok(student);
    }
    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        student = studentService.updateStudent(student);
        return ResponseEntity.ok(student);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student Deleted Successfully.");
    }

}
