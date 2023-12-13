package id.metrodataacademy.serverapp.controllers;

import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.services.EmployeeService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
 @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class EmployeeController {

    private EmployeeService employeeService;
    @PreAuthorize("hasAnyAuthority('READ_USER', 'READ_ADMIN')")
    @GetMapping
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }
    // @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id) {
        return employeeService.delete(id);
    }

    @GetMapping("/profile")
    public Employee getProfile(@RequestParam(name = "name") String username) {
    return employeeService.getProfileByName(username);
  }
}

