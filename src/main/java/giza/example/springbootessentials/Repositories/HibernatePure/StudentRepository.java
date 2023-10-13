package giza.example.springbootessentials.Repositories.HibernatePure;

import giza.example.springbootessentials.Models.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository extends AbstractHibernateDAO<Student> {
    public StudentRepository() {
        setClazz(Student.class);
    }
}
