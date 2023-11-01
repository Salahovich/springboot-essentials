package giza.example.springbootessentials.Integration.Repositories;

import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Models.InstructorDetails;
import giza.example.springbootessentials.Repositories.JPA.InstructorRepositoryJpa;
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
public class InstructorRepositoryTests {

    @Autowired
    InstructorRepositoryJpa repositoryJpa;

    @Test
    public void instructorRepository_findById_returnValidInstructor() {
        UUID uuid = UUID.fromString("9fb801b0-9dac-4162-b202-d2b5968ceeb5");
        Optional<Instructor> instructor = repositoryJpa.findById(uuid);

        Assertions.assertDoesNotThrow(() -> instructor.orElseThrow(NullPointerException::new));
        Assertions.assertEquals(uuid, instructor.get().getId());
    }

    @Test
    public void instructorRepository_findAll_returnAllInstructors() {
        int expected = 5;
        List<Instructor> instructors = (List<Instructor>) repositoryJpa.findAll();

        Assertions.assertEquals(expected, instructors.size());
    }

    @Test
    public void instructorRepository_saveInstructor_returnValidInstructorWithId() {
        Instructor instructor = new Instructor(
                null,
                "Muhammad",
                "Salah",
                "m.salahovich@gmail.com",
                "01211820187",
                "MR",
                new InstructorDetails(null, "@Megamind", "/megamind")
        );

        instructor = repositoryJpa.save(instructor);

        Assertions.assertNotNull(instructor.getId());
    }

    @Test
    public void instructorRepository_deleteInstructorById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("9fb801b0-9dac-4162-b202-d2b5968ceeb5");

        repositoryJpa.deleteById(uuid);
        Optional<Instructor> instructor = repositoryJpa.findById(uuid);

        Assertions.assertThrows(NullPointerException.class, () -> instructor.orElseThrow(NullPointerException::new));
    }

}
