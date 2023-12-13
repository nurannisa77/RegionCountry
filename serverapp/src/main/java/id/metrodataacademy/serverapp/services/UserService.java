package id.metrodataacademy.serverapp.services;

import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.models.User;
import id.metrodataacademy.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private RoleService roleService;


    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Integer id) {
        return this.userRepository.findById(id).orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }

   public User update(Integer id, User user) {
    getById(id);
    user.setId(id);
    return userRepository.save(user);
    }

    //add role
    public User addRole(Integer id, Role role){
        //cek di user
        User user = getById(id);

        //cek role & set role
        List<Role> roles = user.getRoles();
        roles.add(roleService.getById(role.getId()));
        user.setRoles(roles);

        //save
        return userRepository.save(user);
    }
    public List<User> searchByUsernameJPQL(String username) {
        return userRepository.findUserByUsername(username);
    

    }
}
