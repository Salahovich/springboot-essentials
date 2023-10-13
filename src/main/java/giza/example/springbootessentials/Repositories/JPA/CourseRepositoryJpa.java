package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseRepositoryJpa extends CrudRepository<Course, UUID> {
}
