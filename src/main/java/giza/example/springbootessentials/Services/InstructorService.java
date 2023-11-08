package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Caching.RedisService;
import giza.example.springbootessentials.Exceptions.InstructorNotFoundException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Repositories.JPA.InstructorRepositoryJpa;
import giza.example.springbootessentials.Validations.InstructorValidation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Log
public class InstructorService {

    @Autowired
    private InstructorRepositoryJpa repository;

    @Autowired
    private InstructorValidation validation;

    @Autowired
    private RedisService redisService;

    public Iterable<Instructor> findAll(){
        return repository.findAll();
    }

    public Instructor findInstructorById(UUID uuid){
        if(redisService.hasKey(uuid.toString())){
            log.info("Instructor Cache HIT");
            return  (Instructor) redisService.getValueFromRedis(uuid.toString()).get();
        }else{
            Instructor instructor = repository.findById(uuid).orElseThrow(InstructorNotFoundException::new);
            redisService.setValueInRedis(instructor.getId().toString(), instructor);
            log.info("Instructor Cache MISS");
            return instructor;
        }
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
        if(findInstructorById(instructor.getId()) == null)
            throw new InstructorNotFoundException();

        instructor = saveInstructor(instructor);
        if(redisService.hasKey(instructor.getId().toString())){
            redisService.setValueInRedis(instructor.getId().toString(), instructor);
            log.info("Updating Cache for instructor");
        }

        return instructor;
    }

    public void deleteInstructorById(UUID id){
        repository.findById(id).orElseThrow(InstructorNotFoundException::new);
        repository.deleteById(id);
        redisService.deleteKey(id.toString());
        log.info("Deleting Cache for instructor");
    }

    protected void checkEmail(String email){
        validation.checkEmail(email);
    }

    protected void checkPhone(String phoneNumber){
        validation.checkPhoneNumber(phoneNumber);
    }

}
