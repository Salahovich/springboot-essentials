package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.InstructorNotFoundException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Repositories.JPA.InstructorRepositoryJpa;
import giza.example.springbootessentials.Validations.InstructorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepositoryJpa repository;

    @Autowired
    private InstructorValidation validation;

    public Iterable<Instructor> findAll(){
        return repository.findAll();
    }

    public Instructor findInstructorById(UUID uuid){
        return repository.findById(uuid).orElseThrow(InstructorNotFoundException::new);
    }

    public List<Map<String, Object>> findInstructorsWithTheirCourses(){
        return repository.findAllInstructorsWithCourses();
    }

    public List<Map<String, Object>> findInstructorsAndTheirStudents(){
        return repository.findAllInstructorsAndTheirStudents();
    }
    public Instructor saveInstructor(Instructor instructor){
        instructor.setEmail(instructor.getEmail().toLowerCase());

        checkEmail(instructor.getEmail());
        checkPhone(instructor.getPhoneNumber());

        return repository.save(instructor);
    }

    public Instructor updateInstructor(Instructor instructor){
        return saveInstructor(instructor);
    }

    public void deleteInstructorById(UUID id){
        repository.findById(id).orElseThrow(InstructorNotFoundException::new);
        repository.deleteById(id);
    }

    protected void checkEmail(String email){
        validation.checkEmail(email);
    }

    protected void checkPhone(String phoneNumber){
        validation.checkPhoneNumber(phoneNumber);
    }

}
