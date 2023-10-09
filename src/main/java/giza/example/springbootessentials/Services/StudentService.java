package giza.example.springbootessentials.Services;

import giza.example.springbootessentials.Models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll(){
        return students;
    }

    public Student findStudentById(int id){
        var student = students.stream().filter(s -> s.getId() == id).findFirst();
        return student.orElse(null);
    }

    public List<Student> addStudent(Student student){
        students.add(student);
        return students;
    }

    public List<Student> updateStudent(Student student){
        Student stu = findStudentById(student.getId());
        if(stu != null){
            stu.clone(student);
            return students;
        }
        return null;
    }

    public List<Student> deleteStudentById(int id){
        students.removeIf(s -> s.getId() == id);
        return students;
    }
}
