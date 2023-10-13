package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InstructorRepositoryJpa extends CrudRepository<Instructor, UUID> {
}
