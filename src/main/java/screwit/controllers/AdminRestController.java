package screwit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import screwit.model.SimplifiedUser;
import screwit.model.User;
import screwit.service.UserDetailsServiceImpl;
import screwit.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin/v1/users")
public class AdminRestController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;



    @Autowired
    public AdminRestController(UserService userService, PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplifiedUser> getUserById(@PathVariable("id") Long id){

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SimplifiedUser simplifiedUser = new SimplifiedUser(user);

        return new ResponseEntity<>(simplifiedUser, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplifiedUser> saveCustomer(@RequestBody  SimplifiedUser simplifiedUser) {
        HttpHeaders headers = new HttpHeaders();

        if (simplifiedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        simplifiedUser.setPassword(passwordEncoder.encode(simplifiedUser.getPassword()));

        User user = new User(simplifiedUser);

        userService.add(user);


        simplifiedUser.setId(userService.findByUsername(simplifiedUser.getUsername()).getId());

        return new ResponseEntity<>(simplifiedUser, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplifiedUser> updateCustomer(@RequestBody  SimplifiedUser simplifiedUser) {
        HttpHeaders headers = new HttpHeaders();

        if (simplifiedUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        simplifiedUser.setPassword(passwordEncoder.encode(simplifiedUser.getPassword()));

        User updatedUser = new User(simplifiedUser);
        userService.edit(updatedUser.getId(), updatedUser);

        return new ResponseEntity<>(simplifiedUser, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SimplifiedUser> deleteCustomer(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SimplifiedUser>> getAllCustomers() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<SimplifiedUser> simplifiedUsers = new ArrayList<>();

        for (User user: users) {
            simplifiedUsers.add(new SimplifiedUser(user));
        }

        return new ResponseEntity<>(simplifiedUsers, HttpStatus.OK);
    }
}
