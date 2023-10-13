package giza.example.springbootessentials.Repositories.HibernatePure;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Component
public abstract class AbstractHibernateDAO<T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    private EntityManager manager;
    public void setClazz(Class<T> clazzToSet){
        clazz = clazzToSet;
    }

    public Iterable<T> findAll(){
        Session session = getSession();
        List<T> list = session.createQuery("FROM " + clazz.getName(), clazz).list();
        session.close();
        return list;
    }

    public T findById(UUID id){
        Session session = getSession();
        T entity = session.get(clazz, id);
        session.close();
        return entity;
    }

    @Transactional
    public T save(T entity) {
        Session session = getSession();
        session.persist(entity);
        session.close();
        return entity;
    }
    @Transactional
    public T update(T entity) {
        Session session = getSession();
        entity = (T) session.merge( entity );
        session.close();
        return entity;
    }
    @Transactional
    public void delete(T entity) {
        Session session = getSession();
        session.remove(entity);
        session.close();
    }
    @Transactional
    public void deleteById(UUID uuid) {
        final T entity = findById(uuid);
        delete(entity);
    }
    public Session getSession(){
        return manager.unwrap(Session.class);
    }
}
