package giza.example.springbootessentials.Unit.Validations;

import giza.example.springbootessentials.Exceptions.CourseNameAlreadyExists;
import giza.example.springbootessentials.Models.Course;
import giza.example.springbootessentials.Repositories.Validations.CourseValidationRepository;
import giza.example.springbootessentials.Validations.CourseValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CourseValidationTests {
    @Mock
    private CourseValidationRepository repository;

    @InjectMocks
    private CourseValidation validation;

    @Test
    public void instructorValidation_checkName_ThrowCourseAlreadyExistsException(){
        Mockito.when(repository.findCourseByName(Mockito.any())).thenReturn(Optional.of(new Course()));

        Assertions.assertThrows(CourseNameAlreadyExists.class, () -> validation.checkName("Arabic"));
    }
}
