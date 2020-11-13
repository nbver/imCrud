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
public class UserServiceImpl implements UserService {

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
        return userDao.findAll();
    }
    @Transactional
    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }
    @Transactional
    @Override
    public void edit(long id, User user) {
        //userDao.deleteById(id);
        //Set<Role> roleSet = new HashSet<>();
       // roleSet.add(roleDao.findByRole("ROLE_USER"));
        //user.setRoles(roleSet);
        userDao.save(user);
    }
    @Transactional
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public Set<Role> findAllRoles() {
        return new HashSet<>(roleDao.findAll());
    }
    @Transactional
    @Override
    public Role findRole(String role) {
        return roleDao.findByRole(role);
    }


}
