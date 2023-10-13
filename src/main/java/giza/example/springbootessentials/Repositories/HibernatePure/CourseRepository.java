package giza.example.springbootessentials.Repositories.HibernatePure;

import giza.example.springbootessentials.Models.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository extends AbstractHibernateDAO<Course> {

    public CourseRepository(){
        setClazz(Course.class);
    }
}
