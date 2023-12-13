package id.metrodataacademy.serverapp.services;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import id.metrodataacademy.serverapp.dto.EmployeeDTO;
import id.metrodataacademy.serverapp.dto.Request.LoginRequest;
import id.metrodataacademy.serverapp.dto.response.LoginResponse;
import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.models.Role;
import id.metrodataacademy.serverapp.models.User;
import id.metrodataacademy.serverapp.repositories.EmployeeRepository;
import id.metrodataacademy.serverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private AppUserDetailService appUserDetailService;

    public Employee registration(EmployeeDTO employeeDTO){
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        User user = modelMapper.map(employeeDTO, User.class);

        //set password
        user.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleService.getById(2));
        user.setRoles(roles);

        employee.setUser(user);
        user.setEmployee(employee);

        return employeeRepository.save(employee);


    }
    public LoginResponse login(LoginRequest loginRequest){
        // set login request
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()
        );

        // set principle
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContextHolder.getContext().setAuthentication(auth);

        // set response
        User user = userRepository
      .findByUsernameOrEmployeeEmail(
        loginRequest.getUsername(),
        loginRequest.getUsername()
      )
      .get();

      UserDetails userDetails = appUserDetailService.loadUserByUsername(
        loginRequest.getUsername()
      );
  
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setUsername(user.getUsername());
    loginResponse.setEmail(user.getEmployee().getEmail());
    loginResponse.setAuthorities(
    userDetails
        .getAuthorities()
        .stream()
        .map(authority -> authority.getAuthority())
        .collect(Collectors.toList())
    );
    
     
        return loginResponse;
    }
    
}
