package screwit.service;

import screwit.model.Role;
import screwit.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserById(long id);
    List<User> getAllUsers();
    void delete(long id);
    void edit(long id, User user);
    void add(User user);
    Set<Role> findAllRoles();
    Role findRole(String role);
    User findByUsername(String username);
}
