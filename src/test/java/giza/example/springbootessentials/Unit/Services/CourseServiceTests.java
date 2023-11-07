package giza.example.springbootessentials.Unit.Services;

import giza.example.springbootessentials.Exceptions.CourseNotFoundException;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Repositories.JPA.CourseRepositoryJpa;
import giza.example.springbootessentials.Services.CourseService;
import giza.example.springbootessentials.Validations.CourseValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
    @Mock
    private CourseRepositoryJpa repository;

    @Mock
    private CourseValidation validation;

    @InjectMocks
    private CourseService service;

    @Test
    public void courseService_findAll_returnAllCourses(){
        List<Course> actual = Arrays.asList(new Course(), new Course(), new Course());
        Mockito.when(repository.findAll()).thenReturn(actual);

        List<Course> expected = (List<Course>) service.findAll();

        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void courseService_findById_thenReturnCourse(){
        Course actual = new Course();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(actual));

        Course expected = service.findCourseById(UUID.randomUUID());

        Assertions.assertSame(expected, actual);
        Assertions.assertDoesNotThrow(() -> service.findCourseById(UUID.randomUUID()));
    }

    @Test
    public void courseService_findById_thenThrowCourseNotFound(){
        Course actual = new Course();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(CourseNotFoundException.class, () -> service.findCourseById(UUID.randomUUID()));
    }

    @Test
    public void courseService_saveCourse_thenReturnCourseEmailLowerCase(){
        Course actual = new Course();
        actual.setName("ArAbiC");

        Mockito.when(repository.save(Mockito.any())).thenReturn(actual);
        Mockito.doNothing().when(validation).checkName(Mockito.any());

        service.saveCourse(actual);
        Assertions.assertEquals("arabic", actual.getName());
    }

    @Test
    public void courseService_deleteCourse_thenThrowCourseNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(CourseNotFoundException.class, () -> service.deleteCourseById(UUID.randomUUID()));
    }

    @Test
    public void courseService_deleteCourse_thenDoesNotThrowCourseNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(new Course()));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        Assertions.assertDoesNotThrow(() -> service.deleteCourseById(UUID.randomUUID()));
    }
}
