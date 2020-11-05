package screwit.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import screwit.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    public UserDaoImpl() {
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
         entityManager.merge(user);
    }

    @Override
    public void add(User user) {
        entityManager.persist( user );
    }

    @Override
    public UserDetails getUserByUsername(String s) {
        List<User> users = new ArrayList<>();
        Query query = entityManager.createQuery( "from User where username = :username");
        query.setParameter("username", s);
        users =  query.getResultList();

        if (!users.isEmpty()){
            return users.get(0);
        }

        return null;
    }
}
