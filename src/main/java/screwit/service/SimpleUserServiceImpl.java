package screwit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import screwit.dao.JpaDaoImpl;
import screwit.dao.SimpleUserDaoImp;
import screwit.dao.UserDao;
import screwit.model.User;

import java.util.List;

@Service
public class SimpleUserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
    @Transactional
    @Override
    public void edit(int id, User user) {
        userDao.edit(id, user);
    }
    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }
}
