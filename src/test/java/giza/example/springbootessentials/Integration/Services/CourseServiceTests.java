package giza.example.springbootessentials.Integration.Services;

import giza.example.springbootessentials.Enums.CourseLevel;
import giza.example.springbootessentials.Exceptions.CourseNotFoundException;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Models.InstructorDetails;
import giza.example.springbootessentials.Services.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
public class CourseServiceTests {

    @Autowired
    CourseService service;

    @Test
    public void courseService_findById_returnNotNull() {
        UUID uuid = UUID.fromString("11de79bc-5d74-4ea4-a4c2-b7fed6e38913");

        Assertions.assertDoesNotThrow(() -> service.findCourseById(uuid));
        Assertions.assertEquals(uuid, service.findCourseById(uuid).getId());
    }

    @Test
    public void courseService_findAll_returnAllCourses() {
        int expected = 5;
        List<Course> courses = (List<Course>) service.findAll();

        Assertions.assertEquals(expected, courses.size());
    }

    @Test
    public void courseService_saveCourse_returnValidCourseWithId() {
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

        course = service.saveCourse(course);

        Assertions.assertNotNull(course.getId());
    }

    @Test
    public void courseService_deleteCourseById_deleteFromDatabasePermanently() {
        UUID uuid = UUID.fromString("11de79bc-5d74-4ea4-a4c2-b7fed6e38913");

        service.deleteCourseById(uuid);

        Assertions.assertThrows(CourseNotFoundException.class, () -> service.findCourseById(uuid));
    }
}
