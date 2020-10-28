package screwit.service;

import screwit.model.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    List<User> getAllUsers();
    void delete(int id);
    void edit(int id, User user);
    void add(User user);
}
