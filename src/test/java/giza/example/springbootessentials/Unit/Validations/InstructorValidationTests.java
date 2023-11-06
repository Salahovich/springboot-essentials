package giza.example.springbootessentials.Unit.Validations;

import giza.example.springbootessentials.Exceptions.EmailFormatException;
import giza.example.springbootessentials.Exceptions.InstructorAlreadyExistException;
import giza.example.springbootessentials.Exceptions.PhoneNumberFormatException;
import giza.example.springbootessentials.Models.Instructor;
import giza.example.springbootessentials.Repositories.Validations.InstructorValidationRepository;
import giza.example.springbootessentials.Validations.InstructorValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InstructorValidationTests {
    @Mock
    private InstructorValidationRepository repository;

    @InjectMocks
    private InstructorValidation validation;

    @Test
    public void instructorValidation_checkEmail_DoestNotThrowAnything(){
        Mockito.when(repository.findInstructorByEmail(Mockito.any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> validation.checkEmail("m.salahovich@gmail.com"));
    }

    @Test
    public void instructorValidation_checkEmail_ThrowInstructorAlreadyExistException(){
        Mockito.when(repository.findInstructorByEmail(Mockito.any())).thenReturn(new Instructor());

        Assertions.assertThrows(InstructorAlreadyExistException.class, () -> validation.checkEmail("m.salahovich@gmail.com"));
    }

    @Test
    public void instructorValidation_checkEmail_ThrowEmailFormatException(){
        Assertions.assertThrows(EmailFormatException.class, () -> validation.checkEmail("m.salahovichgmail.com"));
    }

    // PhoneNumber testing
    @Test
    public void instructorValidation_checkPhoneNumber_DoestNotThrowAnything(){
        Mockito.when(repository.findInstructorByPhoneNumber(Mockito.any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> validation.checkPhoneNumber("01211820187"));
    }

    @Test
    public void instructorValidation_checkPhoneNumber_ThrowInstructorAlreadyExistException(){
        Mockito.when(repository.findInstructorByPhoneNumber(Mockito.any())).thenReturn(new Instructor());

        Assertions.assertThrows(InstructorAlreadyExistException.class, () -> validation.checkPhoneNumber("01211820187"));
    }

    @Test
    public void instructorValidation_checkPhoneNumber_ThrowPhoneNumberFormatException(){
        Assertions.assertThrows(PhoneNumberFormatException.class, () -> validation.checkPhoneNumber("0121182fdf0187"));
    }

}
