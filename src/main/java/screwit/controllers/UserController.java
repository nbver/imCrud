package screwit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import screwit.model.User;
import screwit.service.SimpleUserServiceImpl;
import screwit.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    UserService us = new SimpleUserServiceImpl();

    @GetMapping
    public String showAllUsers(Model model){
        List<User> users = us.getAllUsers();
        model.addAttribute("users", users);
        return "/users/users";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") int id){
        User user = us.getUserById(id);
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
        us.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("user", us.getUserById(id));
        return  "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id){
        us.edit(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        us.delete(id);
        return "redirect:/users";
    }
}
