package giza.example.springbootessentials.Repositories.Validations;

import giza.example.springbootessentials.Models.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InstructorValidationRepository extends CrudRepository<Instructor, UUID> {

    Instructor findInstructorByPhoneNumber(String phoneNumber);
    Instructor findInstructorByEmail(String email);
}
