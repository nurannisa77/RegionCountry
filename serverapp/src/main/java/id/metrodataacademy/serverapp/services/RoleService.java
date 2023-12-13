package id.metrodataacademy.serverapp.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.repositories.RoleRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new 
        ResponseStatusException(HttpStatus.NOT_FOUND, "not found!!!"));
    }

    public Role update (Integer id, Role role) {
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role delete (Integer id) {
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }

    public Role create(Role role) {
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role name must required ");
        }
        return roleRepository.save(role);
    }

    
}
