package com.example.JpaHibernate.repository;

import com.example.JpaHibernate.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Users save(Users user) {
        return entityManager.merge(user);
    }

    @Transactional
    public boolean delete(long id_user) {
        Users user = getById(id_user);
        if(user != null) {
            entityManager.remove(user);
            return true;
        } else {
            return false;
        }
    }

    public List<Users> getAll() {
        return entityManager.createQuery("SELECT u FROM Users u").getResultList();
    }

    public Users getById(long id_user) {
        return entityManager.find(Users.class, id_user);
    }

    public List<Users> getByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM Users u WHERE u.email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }
}
