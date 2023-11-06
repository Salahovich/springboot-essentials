package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StudentRepositoryJpa extends CrudRepository<Student, UUID> {
    @Query("select course.name, course.students " +
            "from Course course " +
            "join course.students " +
            "where course.level = 'INTERMEDIATE'")
    List<Map<String, Object>> findStudentsEnrolledInIntermediateCourses();
}
