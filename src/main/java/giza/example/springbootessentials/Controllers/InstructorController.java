package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.DTO.InstructorDto;
import giza.example.springbootessentials.EntityMappers.InstructorMapper;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Services.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/university/instructors")
public class InstructorController {
    @Autowired
    private InstructorService service;
    @GetMapping
    public ResponseEntity<List<InstructorDto>> findAll(){
        List<Instructor> instructors = (List<Instructor>) service.findAll();
        return ResponseEntity.ok(instructors.stream().map(s->new InstructorMapper().convertToDto(s)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstructorDto> findById(@PathVariable UUID id){
        Instructor instructor = service.findInstructorById(id);
        return ResponseEntity.ok(new InstructorMapper().convertToDto(instructor));
    }
    @GetMapping("/with-courses")
    public ResponseEntity<List<Map<String, Object>>> InstructorsWithCourses(){
        var tuples = service.findInstructorsWithTheirCourses();
        return ResponseEntity.ok(tuples);
    }
    @GetMapping("/with-students")
    public ResponseEntity<List<Map<String, Object>>> InstructorsAndTheirStudents(){
        var tuples = service.findInstructorsAndTheirStudents();
        return ResponseEntity.ok(tuples);
    }
    @PostMapping
    public ResponseEntity<InstructorDto> addInstructor(@RequestBody @Valid InstructorDto instructorDto){
        InstructorMapper mapper = new InstructorMapper();
        Instructor instructor = service.saveInstructor(mapper.convertToEntity(instructorDto));
        return new ResponseEntity<InstructorDto>(mapper.convertToDto(instructor), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<InstructorDto> updateInstructor(@RequestBody InstructorDto instructorDto){
        InstructorMapper mapper = new InstructorMapper();
        Instructor instructor = service.updateInstructor(mapper.convertToEntity(instructorDto));
        return ResponseEntity.ok(mapper.convertToDto(instructor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable UUID id){
        service.deleteInstructorById(id);
        return ResponseEntity.ok("Instructor Deleted Successfully.");
    }
}
