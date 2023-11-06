package giza.example.springbootessentials.Unit.Validations;

import giza.example.springbootessentials.Exceptions.EmailFormatException;
import giza.example.springbootessentials.Exceptions.NationalIdFormatException;
import giza.example.springbootessentials.Exceptions.PhoneNumberFormatException;
import giza.example.springbootessentials.Exceptions.StudentAlreadyExistException;
import giza.example.springbootessentials.Models.Student;
import giza.example.springbootessentials.Repositories.Validations.StudentValidationRepository;
import giza.example.springbootessentials.Validations.StudentValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentValidationTests {

    @Mock
    private StudentValidationRepository repository;

    @InjectMocks
    private StudentValidation validation;

    @Test
    public void studentValidation_checkEmail_DoestNotThrowAnything(){
        Mockito.when(repository.findStudentByEmail(Mockito.any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> validation.checkEmail("m.salahovich@gmail.com"));
    }

    @Test
    public void studentValidation_checkEmail_ThrowStudentAlreadyExistException(){
        Mockito.when(repository.findStudentByEmail(Mockito.any())).thenReturn(new Student());

        Assertions.assertThrows(StudentAlreadyExistException.class, () -> validation.checkEmail("m.salahovich@gmail.com"));
    }

    @Test
    public void studentValidation_checkEmail_ThrowEmailFormatException(){
        Assertions.assertThrows(EmailFormatException.class, () -> validation.checkEmail("m.salahovichgmail.com"));
    }

    // PhoneNumber testing
    @Test
    public void studentValidation_checkPhoneNumber_DoestNotThrowAnything(){
        Mockito.when(repository.findStudentByPhoneNumber(Mockito.any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> validation.checkPhone("01211820187"));
    }

    @Test
    public void studentValidation_checkPhoneNumber_ThrowStudentAlreadyExistException(){
        Mockito.when(repository.findStudentByPhoneNumber(Mockito.any())).thenReturn(new Student());

        Assertions.assertThrows(StudentAlreadyExistException.class, () -> validation.checkPhone("01211820187"));
    }

    @Test
    public void studentValidation_checkPhoneNumber_ThrowPhoneNumberFormatException(){
        Assertions.assertThrows(PhoneNumberFormatException.class, () -> validation.checkPhone("0121182fdf0187"));
    }

    // NationalId testing
    @Test
    public void studentValidation_checkNationalId_DoestNotThrowAnything(){
        Mockito.when(repository.findStudentByNationalId(Mockito.any())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> validation.checkNationalId("3410120321456985"));
    }

    @Test
    public void studentValidation_checkNationalId_ThrowStudentAlreadyExistException(){
        Mockito.when(repository.findStudentByNationalId(Mockito.any())).thenReturn(new Student());

        Assertions.assertThrows(StudentAlreadyExistException.class, () -> validation.checkNationalId("2313214654545645"));
    }


    @Test
    public void studentValidation_checkNationalId_ThrowNationalIdFormatException(){
        Assertions.assertThrows(NationalIdFormatException.class, () -> validation.checkNationalId("2231212f1gfg213121"));
    }


}
