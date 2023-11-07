package giza.example.springbootessentials.Unit.Services;

import giza.example.springbootessentials.Exceptions.InstructorNotFoundException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Repositories.JPA.InstructorRepositoryJpa;
import giza.example.springbootessentials.Services.InstructorService;
import giza.example.springbootessentials.Validations.InstructorValidation;
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
public class InstructorServiceTests {

    @Mock
    private InstructorRepositoryJpa repository;

    @Mock
    private InstructorValidation validation;

    @InjectMocks
    private InstructorService service;


    @Test
    public void instructorService_findAll_returnAllInstructors(){
        List<Instructor> actual = Arrays.asList(new Instructor(), new Instructor(), new Instructor());
        Mockito.when(repository.findAll()).thenReturn(actual);

        List<Instructor> expected = (List<Instructor>) service.findAll();

        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    public void instructorService_findById_thenReturnInstructor(){
        Instructor actual = new Instructor();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(actual));

        Instructor expected = service.findInstructorById(UUID.randomUUID());

        Assertions.assertSame(expected, actual);
        Assertions.assertDoesNotThrow(() -> service.findInstructorById(UUID.randomUUID()));
    }

    @Test
    public void instructorService_findById_thenThrowInstructorNotFound(){
        Instructor actual = new Instructor();

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(InstructorNotFoundException.class, () -> service.findInstructorById(UUID.randomUUID()));
    }

    @Test
    public void instructorService_saveInstructor_thenReturnInstructorEmailLowerCase(){
        Instructor actual = new Instructor();
        actual.setEmail("M.salaHovicH@Gmail.com");

        Mockito.when(repository.save(Mockito.any())).thenReturn(actual);

        Mockito.doNothing().when(validation).checkEmail(Mockito.any());
        Mockito.doNothing().when(validation).checkPhoneNumber(Mockito.any());

        service.saveInstructor(actual);
        Assertions.assertEquals("m.salahovich@gmail.com", actual.getEmail());
    }

    @Test
    public void instructorService_deleteInstructor_thenThrowInstructorNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(InstructorNotFoundException.class, () -> service.deleteInstructorById(UUID.randomUUID()));
    }

    @Test
    public void instructorService_deleteInstructor_thenDoesNotThrowInstructorNotFound(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(new Instructor()));
        Mockito.doNothing().when(repository).deleteById(Mockito.any());

        Assertions.assertDoesNotThrow(() -> service.deleteInstructorById(UUID.randomUUID()));
    }
}
