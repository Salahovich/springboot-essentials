package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {
////
//    @Autowired
//    private StudentRepository repository;

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
        return repository.save(student);
    }

    public Student updateStudent(Student student){
       return null;
    }

    public void deleteStudentById(UUID id){
        repository.deleteById(id);
    }
}
