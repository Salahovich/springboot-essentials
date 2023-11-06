package giza.example.springbootessentials.Validations;

import giza.example.springbootessentials.Exceptions.EmailFormatException;
import giza.example.springbootessentials.Exceptions.NationalIdFormatException;
import giza.example.springbootessentials.Exceptions.PhoneNumberFormatException;
import giza.example.springbootessentials.Exceptions.StudentAlreadyExistException;
import giza.example.springbootessentials.Repositories.Validations.StudentValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentValidation {

    @Autowired
    private StudentValidationRepository repository;

    public void checkEmail(String email){
        email = email.toLowerCase();
        if(!email.matches("[a-z0-9]*[._][a-z0-9]*@[a-z]*.[a-z][a-z][a-z]"))
            throw new EmailFormatException();
        if(repository.findStudentByEmail(email) != null)
            throw new StudentAlreadyExistException();
    }

    public void checkPhone(String phoneNumber){
        if(!phoneNumber.matches("[0-9]*"))
            throw new PhoneNumberFormatException();
        if(repository.findStudentByPhoneNumber(phoneNumber) != null)
            throw new StudentAlreadyExistException();
    }

    public void checkNationalId(String nationalId){
        if(!nationalId.matches("[0-9]*"))
            throw new NationalIdFormatException();
        if(repository.findStudentByNationalId(nationalId) != null)
            throw new StudentAlreadyExistException();
    }
}
