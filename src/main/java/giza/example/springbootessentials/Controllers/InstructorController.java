package giza.example.springbootessentials.Controllers;

import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Services.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/university/instructors")
public class InstructorController {

    @Autowired
    private InstructorService service;
    @GetMapping
    public ResponseEntity<Iterable<Instructor>> findAll(){
        Iterable<Instructor> instructors = service.findAll();
        return ResponseEntity.ok(instructors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable UUID id){
        Instructor instructor = service.findInstructorById(id);
        return ResponseEntity.ok(instructor);
    }
    @PostMapping
    public ResponseEntity<Instructor> addInstructor(@RequestBody @Valid Instructor instructor){
        instructor = service.saveInstructor(instructor);
        return ResponseEntity.ok(instructor);
    }
    @PutMapping
    public ResponseEntity<Instructor> updateInstructor(@RequestBody Instructor instructor){
        instructor = service.updateInstructor(instructor);
        return ResponseEntity.ok(instructor);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable UUID id){
        service.deleteInstructorById(id);
        return ResponseEntity.ok("Instructor Deleted Successfully.");
    }
}
