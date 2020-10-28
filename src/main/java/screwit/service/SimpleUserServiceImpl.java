package screwit.service;

import screwit.dao.SimpleUserDaoImp;
import screwit.dao.UserDao;
import screwit.model.User;

import java.util.List;

public class SimpleUserServiceImpl implements UserService {

    private UserDao userDao = new SimpleUserDaoImp();

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public void edit(int id, User user) {
        userDao.edit(id, user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }
}
