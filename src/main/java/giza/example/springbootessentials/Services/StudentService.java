package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Caching.RedisService;
import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import giza.example.springbootessentials.Validations.StudentValidation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Log
public class StudentService {

    @Autowired
    private StudentValidation validation;

    @Autowired
    private StudentRepositoryJpa repository;

    @Autowired
    private RedisService redisService;

    public Iterable<Student> findAll(){
        return repository.findAll();
    }

    public Student findStudentById(UUID uuid){
        var cachedStudent = redisService.getValueFromRedis(uuid.toString());
        if(cachedStudent.isPresent()){
            log.info("Student Cache HIT");
            return (Student) cachedStudent.get();
        }
        else{
            Student theStudent = repository.findById(uuid).orElseThrow(StudentNotFoundException::new);
            redisService.setValueInRedis(uuid.toString(), theStudent);
            log.info("Student Cache MISS");
            return theStudent;
        }
    }
    public List<Map<String, Object>> findStudentsEnrolledInIntermediateCourses(){
        return repository.findStudentsEnrolledInIntermediateCourses();
    }
    public Student saveStudent(Student student){
        student.setEmail(student.getEmail().toLowerCase());

        checkEmail(student.getEmail());
        checkPhone(student.getPhoneNumber());
        checkNationalId(student.getNationalId());

        return repository.save(student);
    }
    public Student updateStudent(Student student){
        if(findStudentById(student.getId()) == null)
            throw new StudentNotFoundException();
        student = saveStudent(student);

        if(redisService.hasKey(student.getId().toString())){
            redisService.setValueInRedis(student.getId().toString(), student);
            log.info("Updating student in Cache");
        }

        return student;
    }
    public void deleteStudentById(UUID uuid){
        repository.findById(uuid).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(uuid);
        redisService.deleteKey(uuid.toString());
        log.info("Deleting Student from Cache");
    }

    // wrapper validation methods
    protected void checkEmail(String email){
         validation.checkEmail(email);
    }

    protected void checkPhone(String phoneNumber){
         validation.checkPhone(phoneNumber);
    }

    protected void checkNationalId(String nationalId){
         validation.checkNationalId(nationalId);
    }
}
