package id.metrodataacademy.serverapp.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.models.User;
import id.metrodataacademy.serverapp.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.getById(id);
    }


    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @RequestBody User user) {
        return userService.update(id, user);
    }

    // add role
    @PutMapping("add-role/{id}")
    public User addRole(@PathVariable Integer id, @RequestBody Role role) {
        return userService.addRole(id, role);
  }


}
