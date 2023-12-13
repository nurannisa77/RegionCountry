package id.metrodataacademy.serverapp.controllers;

import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.services.RoleService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.getAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Integer id) {
    return roleService.getById(id);
   }
    

    @PostMapping
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Integer id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @DeleteMapping("/{id}")
    public Role delete(@PathVariable Integer id) {
        return roleService.delete(id);
    }


    
}
