package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.DTO.StudentDto;
import giza.example.springbootessentials.EntityMappers.StudentMapper;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Services.StudentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ModelMapper mapper;
    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll(){
        List<Student> students = (List<Student>) studentService.findAll();
        return ResponseEntity.ok(students.stream().map(s->new StudentMapper().convertToDto(s)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable UUID id){
        Student student = studentService.findStudentById(id);
        if(student == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.map(student, StudentDto.class));
    }

    @GetMapping("/in-intermediate-courses")
    public ResponseEntity<List<Map<String, Object>>> findStudentsEnrolledInIntermediateCourses(){
        var intermediateCourses = studentService.findStudentsEnrolledInIntermediateCourses();
        return ResponseEntity.ok(intermediateCourses);
    }
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody @Valid StudentDto studentDto){
        StudentMapper mapper = new StudentMapper();
        Student student = studentService.saveStudent(mapper.convertToEntity(studentDto));
        return ResponseEntity.ok(mapper.convertToDto(student));
    }
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto){
        StudentMapper mapper = new StudentMapper();
        Student student = studentService.updateStudent(mapper.convertToEntity(studentDto));
        return ResponseEntity.ok(mapper.convertToDto(student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student Deleted Successfully.");
    }

}
