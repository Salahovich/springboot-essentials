package giza.example.springbootessentials.Validations;

import giza.example.springbootessentials.Exceptions.EmailFormatException;
import giza.example.springbootessentials.Exceptions.InstructorAlreadyExistException;
import giza.example.springbootessentials.Exceptions.PhoneNumberFormatException;
import giza.example.springbootessentials.Repositories.Validations.InstructorValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorValidation {

    @Autowired
    private InstructorValidationRepository repository;

    public void checkEmail(String email){
        email = email.toLowerCase();
        if(!email.matches("[a-z0-9]*[._][a-z0-9]*@[a-z]*.[a-z][a-z][a-z]"))
            throw new EmailFormatException();
        if(repository.findInstructorByEmail(email.toLowerCase()) != null)
            throw new InstructorAlreadyExistException();
    }

    public void checkPhoneNumber(String phoneNumber){
        if(!phoneNumber.matches("[0-9]*"))
            throw new PhoneNumberFormatException();
        if(repository.findInstructorByPhoneNumber(phoneNumber) != null)
            throw new InstructorAlreadyExistException();
    }
}
