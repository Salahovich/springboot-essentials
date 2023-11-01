package giza.example.springbootessentials.Integration.Repositories;

import giza.example.springbootessentials.Enums.Gender;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Transactional
public class StudentRepositoryTests {

    @Autowired
    StudentRepositoryJpa repositoryJpa;

    @Test
    public void studentRepository_findById_returnValidStudent() {
        UUID uuid = UUID.fromString("cf3860b6-77d5-4c8f-a806-3f59a0e40e88");
        Optional<Student> student = repositoryJpa.findById(uuid);

        Assertions.assertDoesNotThrow(() -> student.orElseThrow(NullPointerException::new));
        Assertions.assertEquals(uuid, student.get().getId());
    }

    @Test
    public void studentRepository_findAll_returnAllStudent() {
        int expected = 10;
        List<Student> students = (List<Student>) repositoryJpa.findAll();

        Assertions.assertEquals(expected, students.size());
    }

    @Test
    public void studentRepository_saveStudent_returnValidStudentWithId() {
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

        student = repositoryJpa.save(student);

        Assertions.assertNotNull(student.getId());
    }

    @Test
    public void studentRepository_deleteStudentById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("cf3860b6-77d5-4c8f-a806-3f59a0e40e88");

        repositoryJpa.deleteById(uuid);
        Optional<Student> student = repositoryJpa.findById(uuid);

        Assertions.assertThrows(NullPointerException.class, () -> student.orElseThrow(NullPointerException::new));
    }

}
