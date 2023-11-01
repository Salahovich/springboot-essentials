package giza.example.springbootessentials.Integration.Repositories;

import giza.example.springbootessentials.Enums.CourseLevel;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Models.InstructorDetails;
import giza.example.springbootessentials.Repositories.JPA.CourseRepositoryJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Transactional
public class CourseRepositoryTests {

    @Autowired
    CourseRepositoryJpa repositoryJpa;

    @Test
    public void courseRepository_findById_returnValidCourse() {
        UUID uuid = UUID.fromString("11de79bc-5d74-4ea4-a4c2-b7fed6e38913");
        Optional<Course> course = repositoryJpa.findById(uuid);

        Assertions.assertDoesNotThrow(() -> course.orElseThrow(NullPointerException::new));
        Assertions.assertEquals(uuid, course.get().getId());
    }

    @Test
    public void courseRepository_findAll_returnAllCourses() {
        int expected = 5;
        List<Course> courses = (List<Course>) repositoryJpa.findAll();

        Assertions.assertEquals(expected, courses.size());
    }

    @Test
    public void courseRepository_saveInstructor_returnValidInstructorWithId() {
        Instructor instructor = new Instructor(
                null,
                "Muhammad",
                "Salah",
                "m.salahovich@gmail.com",
                "01211820187",
                "MR",
                new InstructorDetails(null, "@Megamind", "/megamind")
        );

        Course course = new Course(
                null,
                "Arabic",
                LocalDateTime.now(),
                LocalDateTime.now(),
                CourseLevel.Advanced,
                true,
                instructor,
                null
        );

        course = repositoryJpa.save(course);

        Assertions.assertNotNull(course.getId());
    }

    @Test
    public void courseRepository_deleteInstructorById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("11de79bc-5d74-4ea4-a4c2-b7fed6e38913");

        repositoryJpa.deleteById(uuid);
        Optional<Course> course = repositoryJpa.findById(uuid);

        Assertions.assertThrows(NullPointerException.class, () -> course.orElseThrow(NullPointerException::new));
    }
}
