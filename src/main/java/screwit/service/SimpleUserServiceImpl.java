package screwit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import screwit.dao.RoleDao;
import screwit.dao.UserDao;
import screwit.model.Role;
import screwit.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SimpleUserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Transactional
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }
    @Transactional
    @Override
    public void edit(long id, User user) {
        userDao.edit(id, user);
    }
    @Transactional
    @Override
    public void add(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findByRole("ROLE_USER"));
        user.setRoles(roles);
        userDao.add(user);
    }
}
