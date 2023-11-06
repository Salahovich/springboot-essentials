package giza.example.springbootessentials.Repositories.Validations;

import giza.example.springbootessentials.Models.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface StudentValidationRepository extends CrudRepository<Student, UUID> {

    Student findStudentByEmail(String email);
    Student findStudentByPhoneNumber(String phoneNumber);
    Student findStudentByNationalId(String nationalId);
}
