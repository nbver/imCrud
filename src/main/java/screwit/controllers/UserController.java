package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import screwit.model.Role;
import screwit.model.User;
import screwit.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showAllUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") int id){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/admin/user";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "admin/newuser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id){
        User user = userService.getUserById(id);
        Set<String> userRoles = user.getRolesAsStrings();
        Set<Role> allRoles = userService.findAllRoles();
        Set<String> allStringRoles = allRoles.stream().
                                    map(role -> role.getRole()).
                                    collect(Collectors.toSet());
        Map<String, Boolean> roleMap = new HashMap<>();
        for (String role: allStringRoles) {
            if (userRoles.contains(role)){
                roleMap.put(role, true);
            } else {
                roleMap.put(role, false);
            }
        }

        //User testUser = new User(99, "testName","testLastName");
        model.addAttribute("user", user);
        model.addAttribute("roleMap", roleMap);
        model.addAttribute("allStringRoles", allStringRoles);

        return  "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("list") ArrayList<String> list,
                             @PathVariable("id") long id){

        Set<Role> newRoles = new HashSet<>();
        for(String s: list){
            newRoles.add(userService.findRole(s));
        }
        user.setRoles(newRoles);
        userService.edit(id, user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/users";
    }
}
