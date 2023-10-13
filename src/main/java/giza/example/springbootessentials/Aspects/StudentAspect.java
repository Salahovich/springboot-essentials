package giza.example.springbootessentials.Aspects;

import giza.example.springbootessentials.Models.Student;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class StudentAspect{
    @Around("execution(* giza.example.springbootessentials.Services.StudentService.addStudent(..)) && args(student,..)")
    public Object logNewStudent(ProceedingJoinPoint pjp, Student student) throws Throwable {
        System.out.println("Logging new Student adding to the system.");
        System.out.println(student);
        return pjp.proceed();
    }
    @Before("execution(* giza.example.springbootessentials.Services.StudentService.deleteStudentById(..)) && args(id,..)")
    public void logDeletingStudentById(int id){
        System.out.println("Deleting Student By id = " + id);
    }
    @After("execution(* giza.example.springbootessentials.Services.StudentService.findAll())")
    public void viewAllStudents(){
        System.out.println("Viewing all the students in the system");
    }
    @After("execution(* giza.example.springbootessentials.Services.StudentService.findStudentById(..)) && args(id,..)")
    public void viewStudentById(int id) {
        System.out.println("Viewing student with the id = " + id);
    }
    @Before("execution(* giza.example.springbootessentials.Services.StudentService.updateStudent(..)) && args(student,..)")
    public void updateStudent(Student student){
        System.out.println("Updating student with the name + " + student.getFirstName());
        System.out.println(student);
    }
}
