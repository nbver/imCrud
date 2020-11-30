package screwit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import screwit.model.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {

    User getUserById(long id);
    List<User> findAll();
    void deleteById(long id);
    User save(User user);



    User getUserByUsername(String s);
}
