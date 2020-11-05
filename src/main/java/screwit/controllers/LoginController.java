package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import screwit.model.User;
import screwit.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("users")
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("user/{id}")
    public String showUser(Model model, @PathVariable("id") int id){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user";
    }
}
