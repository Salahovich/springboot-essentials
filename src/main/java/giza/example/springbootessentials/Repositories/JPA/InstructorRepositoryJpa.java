package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InstructorRepositoryJpa extends CrudRepository<Instructor, UUID> {

    @Query("select instructor.firstName as firstName, instructor.lastName as lastName, course.name as courseName, course.id as courseId " +
            "from Instructor instructor " +
            "join Course course On course.instructor = instructor")
    List<Map<String, Object>> findAllInstructorsWithCourses();

    @Query("select instructor.firstName as firstName, instructor.lastName as lastName, course.id as courseId, course.students as courseStudent " +
            "from Course course " +
            "join course.instructor instructor " +
            "join course.students student")
    List<Map<String, Object>> findAllInstructorsAndTheirStudents();
}
