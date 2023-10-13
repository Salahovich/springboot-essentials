package giza.example.springbootessentials.Repositories.HibernatePure;

import giza.example.springbootessentials.Models.Instructor;
import org.springframework.stereotype.Repository;

@Repository
public class InstructorRepository extends AbstractHibernateDAO<Instructor> {
    public InstructorRepository(){
        setClazz(Instructor.class);
    }
}

