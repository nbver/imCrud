package screwit.dao;

import org.springframework.security.core.userdetails.UserDetails;
import screwit.model.User;

import java.util.List;

public interface UserDao {
    User getUserById(long id);
    List<User> getAllUsers();
    void delete(long id);
    void edit(long id, User user);
    void add(User user);

    UserDetails getUserByUsername(String s);
}
