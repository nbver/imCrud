package screwit.dao;

import org.springframework.stereotype.Component;
import screwit.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class SimpleUserDaoImp implements UserDao {

   private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
   private static Map<Integer, User> users = new HashMap<>();

   static {
      User user1= new User(AUTO_ID.getAndIncrement(), "pasha", "pashin");
      User user2 = new User(AUTO_ID.getAndIncrement(), "masha", "masina");
      users.put(user1.getId(), user1);
      users.put(user2.getId(), user2);

   }


   @Override
   public User getUserById(int id) {
      List<User> userList = new ArrayList<>(users.values());
      return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
   }

   @Override
   public List<User> getAllUsers() {
       return new ArrayList<>(users.values());
   }

   @Override
   public void delete(int id) {
      users.remove(id);
   }

   @Override
   public void edit(int id, User user) {
      User userToEdit = users.get(id);
      userToEdit.setName(user.getName());
      userToEdit.setLastName(user.getLastName());
   }

   @Override
   public void add(User user) {
      user.setId(AUTO_ID.getAndIncrement());
      users.put(user.getId(), user);
   }

}
