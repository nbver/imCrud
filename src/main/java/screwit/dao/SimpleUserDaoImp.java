package screwit.dao;

import org.springframework.security.core.userdetails.UserDetails;
import screwit.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class SimpleUserDaoImp implements UserDao {

   private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
   private static Map<Long, User> users = new HashMap<Long, User>();

   static {
      User user1= new User( "pasha", "pashin");
      User user2 = new User( "masha", "masina");
      users.put(user1.getId(), user1);
      users.put(user2.getId(), user2);

   }


   @Override
   public User getUserById(long id) {
      List<User> userList = new ArrayList<>(users.values());
      return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
   }

   @Override
   public List<User> getAllUsers() {
       return new ArrayList<>(users.values());
   }

   @Override
   public void delete(long id) {
      users.remove(id);
   }

   @Override
   public void edit(long id, User user) {
      User userToEdit = users.get(id);
      userToEdit.setUsername(user.getUsername());
      userToEdit.setPassword(user.getPassword());
   }

   @Override
   public void add(User user) {
      user.setId(AUTO_ID.getAndIncrement());
      users.put(user.getId(), user);
   }

   @Override
   public UserDetails getUserByUsername(String s) {
      return null;
   }

}
