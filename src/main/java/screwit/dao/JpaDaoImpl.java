package screwit.dao;

import org.springframework.stereotype.Repository;
import screwit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JpaDaoImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    public JpaDaoImpl() {
    }

    @Override
    public User getUserById(long id) {
        return  entityManager.find(User.class, id );
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users =  entityManager.createQuery( "from User")
                .getResultList();
        return users;
    }

    @Override
    public void delete(long id) {
        entityManager.remove( entityManager.find(User.class, id) );
    }

    @Override
    public void edit(long id, User user) {
         entityManager.merge( user );
    }

    @Override
    public void add(User user) {
        entityManager.persist( user );
    }
}
