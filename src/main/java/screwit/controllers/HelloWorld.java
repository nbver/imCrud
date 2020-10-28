package screwit.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import screwit.model.User;
import screwit.service.SimpleUserServiceImpl;
import screwit.service.UserService;

@Controller
public class HelloWorld {



    @GetMapping("/")
    public String sayHello(){
//        User user2 = us.getUserById(1);
//        model.addAttribute("user", user2.getLastName());
        return "hello";
    }
}
