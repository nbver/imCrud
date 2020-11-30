package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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




    @GetMapping("user/{id}")
    public String showUser(Model model, @PathVariable("id") int id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principalUser =(User) authentication.getPrincipal();
        User user = userService.getUserById(id);
        //if (principalUser.getId() == user.getId()){
            model.addAttribute("user", user);
            return "/user";
//        }
//        return "admin/users";
    }
}
