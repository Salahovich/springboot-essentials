package giza.example.springbootessentials.Repositories.Validations;

import giza.example.springbootessentials.Models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseValidationRepository extends CrudRepository<Course, UUID> {
    Optional<Course> findCourseByName(String name);
}
