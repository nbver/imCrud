package screwit.service;

import screwit.model.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);
    List<User> getAllUsers();
    void delete(long id);
    void edit(long id, User user);
    void add(User user);
}