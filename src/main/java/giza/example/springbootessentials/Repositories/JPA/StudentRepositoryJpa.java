package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepositoryJpa extends CrudRepository<Student, UUID> {
}
