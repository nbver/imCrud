package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal =(User) authentication.getPrincipal();
        model.addAttribute("users", users);
        //model.addAttribute("principal", principal);
        return "admin/users";
    }

    @GetMapping("/{id}")
    public String showUser(Model model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/admin/user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/newuser";
    }

    @PostMapping
    public String saveUser(
            @RequestParam("role_list") ArrayList<String> list,
            @RequestParam("newUsername") String username,
            @RequestParam("newPassword") String password
            ) {

        Set<Role> newRoles = new HashSet<>();
        for(String s: list){
            newRoles.add(userService.findRole(s));
            System.out.println(s);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(newRoles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
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
        user.setPassword("enter new password");
        model.addAttribute("user", user);
        model.addAttribute("roleMap", roleMap);
        model.addAttribute("allStringRoles", allStringRoles);

        return  "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@RequestParam("list") ArrayList<String> list,
                             @RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @PathVariable("id") long id){
        User user = userService.getUserById(id);
        Set<Role> newRoles = new HashSet<>();
        for(String s: list){
            newRoles.add(userService.findRole(s));
        }
        user.setRoles(newRoles);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        userService.edit(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
