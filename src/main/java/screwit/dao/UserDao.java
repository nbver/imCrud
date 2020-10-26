package screwit.dao;

import screwit.model.User;

import java.util.List;

public interface UserDao {
    User getUserById();
    List<User> getAllUsers();
    void delete(User user);
    void update(User user);
    void add(User user);
}
