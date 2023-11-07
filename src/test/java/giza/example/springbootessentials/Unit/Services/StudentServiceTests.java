package giza.example.springbootessentials.Unit.Services;

import giza.example.springbootessentials.Exceptions.StudentNotFoundException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.JPA.StudentRepositoryJpa;
import giza.example.springbootessentials.Services.StudentService;
import giza.example.springbootessentials.Validations.StudentValidation;
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
public class StudentServiceTests {

    @Mock
    private StudentRepositoryJpa repository;

    @Mock
    private StudentValidation validation;

    @InjectMocks
    private StudentService service;


    @Test
    public void studentService_findAll_returnAllStudents(){
        List<Student> actual = Arrays.asList(new Student(), new Student(), new Student());
        Mockito.when(repository.findAll()).thenReturn(actual);

        List<Student> expected = (List<Student>) service.findAll();

        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void studentService_findById_thenReturnStudent(){
        Student actual = new Student();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(actual));

        Student expected = service.findStudentById(UUID.randomUUID());

        Assertions.assertSame(expected, actual);
        Assertions.assertDoesNotThrow(() -> service.findStudentById(UUID.randomUUID()));
    }

    @Test
    public void studentService_findById_thenThrowStudentNotFound(){
        Student actual = new Student();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(StudentNotFoundException.class, () -> service.findStudentById(UUID.randomUUID()));
    }

    @Test
    public void studentService_saveStudent_thenReturnStudentEmailLowerCase(){
        Student actual = new Student();
        actual.setEmail("M.salaHovicH@Gmail.com");

        Mockito.when(repository.save(Mockito.any())).thenReturn(actual);

        Mockito.doNothing().when(validation).checkEmail(Mockito.any());
        Mockito.doNothing().when(validation).checkPhone(Mockito.any());
        Mockito.doNothing().when(validation).checkNationalId(Mockito.any());

        service.saveStudent(actual);
        Assertions.assertEquals("m.salahovich@gmail.com", actual.getEmail());
    }

    @Test
    public void studentService_deleteStudent_thenThrowStudentNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(StudentNotFoundException.class, () -> service.deleteStudentById(UUID.randomUUID()));
    }

    @Test
    public void studentService_deleteStudent_thenDoesNotThrowStudentNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(new Student()));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        Assertions.assertDoesNotThrow(() -> service.deleteStudentById(UUID.randomUUID()));
    }
}

