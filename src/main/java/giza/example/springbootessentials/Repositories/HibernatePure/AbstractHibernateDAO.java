package giza.example.springbootessentials.Repositories.HibernatePure;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Repository
public abstract class AbstractHibernateDAO<T extends Serializable> {
    private Class<T> clazz;

    @Autowired
    private EntityManager manager;
    public void setClazz(Class<T> clazzToSet){
        clazz = clazzToSet;
    }
    @Transactional

    public Iterable<T> findAll(){
        Session session = getSession();
        List<T> list = session.createQuery("FROM " + clazz.getName(), clazz).list();
        System.out.println(list);
        return list;
    }
    @Transactional

    public T findById(UUID id){
        Session session = getSession();
        T entity = session.get(clazz, id);
        return entity;
    }

    @Transactional
    public T save(T entity) {
        Session session = getSession();
        session.persist(entity);
        return entity;
    }
    @Transactional
    public T update(T entity) {
        Session session = getSession();
        entity = (T) session.merge( entity );
        return entity;
    }
    @Transactional
    public void delete(T entity) {
        Session session = getSession();
        session.remove(entity);
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
