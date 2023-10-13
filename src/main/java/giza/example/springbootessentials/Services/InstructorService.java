package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.InstructorNotFoundException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Repositories.JPA.InstructorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InstructorService {
//    @Autowired
//    private InstructorRepository repository;

    @Autowired
    private InstructorRepositoryJpa repository;
    public Iterable<Instructor> findAll(){
        return repository.findAll();
    }

    public Instructor findInstructorById(UUID uuid){
        return repository.findById(uuid).orElseThrow(InstructorNotFoundException::new);
    }

    public Instructor saveInstructor(Instructor instructor){
        return repository.save(instructor);
    }

    public Instructor updateInstructor(Instructor instructor){
        return null;
    }

    public void deleteInstructorById(UUID id){
        repository.deleteById(id);
    }
}