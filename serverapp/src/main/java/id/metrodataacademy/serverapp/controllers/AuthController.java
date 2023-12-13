package id.metrodataacademy.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import id.metrodataacademy.serverapp.dto.EmployeeDTO;
import id.metrodataacademy.serverapp.dto.Request.LoginRequest;
import id.metrodataacademy.serverapp.dto.response.LoginResponse;
import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.services.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping
public class AuthController {
    private AuthService authService;

    @PostMapping("/registration")
    public Employee registration(@RequestBody EmployeeDTO employeeDTO){
        return authService.registration(employeeDTO);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
    
}
