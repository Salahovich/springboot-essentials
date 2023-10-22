package giza.example.springbootessentials.Repositories.JPA;

import giza.example.springbootessentials.Models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CourseRepositoryJpa extends CrudRepository<Course, UUID> {
   @Query("select course.name, course.startDate, course.students " +
           "from Course course " +
           "join course.students")
    List<Map<String, Object>> findCoursesAndStudentsEnrolledIn();

}
