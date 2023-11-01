package giza.example.springbootessentials.Integration.Services;

import giza.example.springbootessentials.Exceptions.InstructorNotFoundException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Models.InstructorDetails;
import giza.example.springbootessentials.Services.InstructorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@SpringBootTest
public class InstructorServiceTests {

    @Autowired
    InstructorService service;

    @Test
    public void instructorService_findById_returnValidInstructor() {
        UUID uuid = UUID.fromString("9fb801b0-9dac-4162-b202-d2b5968ceeb5");

        Assertions.assertDoesNotThrow(() -> service.findInstructorById(uuid));
        Assertions.assertEquals(uuid, service.findInstructorById(uuid).getId());
    }

    @Test
    public void instructorService_findAll_returnAllInstructors() {
        int expected = 5;
        List<Instructor> instructors = (List<Instructor>) service.findAll();

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

        instructor = service.saveInstructor(instructor);

        Assertions.assertNotNull(instructor.getId());
    }

    @Test
    public void instructorService_deleteInstructorById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("9fb801b0-9dac-4162-b202-d2b5968ceeb5");

        service.deleteInstructorById(uuid);

        Assertions.assertThrows(InstructorNotFoundException.class, () -> service.findInstructorById(uuid));
    }
}
