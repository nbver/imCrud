package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import screwit.model.Role;
import screwit.model.User;
import screwit.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService ;




    @GetMapping
    public String showAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "/users/users";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") int id){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/users/user";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "users/newuser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){
//        User userToAdd = new User();
//        userToAdd.setUsername(user.getUsername());
//        userToAdd.setPassword(user.getPassword());

        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        //User testUser = new User(99, "testName","testLastName");
        model.addAttribute("user", userService.getUserById(id));
        return  "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.edit(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/users";
    }
}
