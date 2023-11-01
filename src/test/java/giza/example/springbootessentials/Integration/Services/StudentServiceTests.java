package giza.example.springbootessentials.Integration.Services;

import giza.example.springbootessentials.Enums.Gender;
import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Services.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@SpringBootTest
public class StudentServiceTests {

    @Autowired
    private StudentService service;

    @Test
    public void studentService_findById_returnValidStudent() {
        UUID uuid = UUID.fromString("cf3860b6-77d5-4c8f-a806-3f59a0e40e88");

        Assertions.assertDoesNotThrow(() -> service.findStudentById(uuid));
        Assertions.assertEquals(uuid, service.findStudentById(uuid).getId());
    }

    @Test
    public void studentService_findAll_returnAllStudent() {
        int expected = 10;
        List<Student> students = (List<Student>) service.findAll();

        Assertions.assertEquals(expected, students.size());
    }

    @Test
    public void studentService_saveStudent_returnValidStudentWithId() {
        Student student = new Student(
                null,
                "Muhammad",
                "Salah",
                20,
                Gender.Male,
                "m.salahovich@gmail.com",
                "01211820187",
                "55464564564646"
        );

        student = service.saveStudent(student);

        Assertions.assertNotNull(student.getId());
    }

    @Test
    public void studentService_deleteStudentById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("cf3860b6-77d5-4c8f-a806-3f59a0e40e88");

        service.deleteStudentById(uuid);

        Assertions.assertThrows(StudentNotFoundException.class, () -> service.findStudentById(uuid));
    }
}
