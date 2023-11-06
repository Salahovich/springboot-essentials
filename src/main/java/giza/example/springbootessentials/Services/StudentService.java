package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import giza.example.springbootessentials.Validations.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentValidation validation;

    @Autowired
    private StudentRepositoryJpa repository;

    public Iterable<Student> findAll(){
        return repository.findAll();
    }

    public Student findStudentById(UUID uuid){
        return repository.findById(uuid).orElseThrow(StudentNotFoundException::new);
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
        return saveStudent(student);
    }
    public void deleteStudentById(UUID uuid){
        repository.findById(uuid).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(uuid);
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
