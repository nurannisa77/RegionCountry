package id.metrodataacademy.serverapp.services;

import id.metrodataacademy.serverapp.models.Employee;
import id.metrodataacademy.serverapp.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }


    public  Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Employee not found!"));
    }
   
    public Employee getProfileByName(String username) {
        return employeeRepository.findEmployeeByUsername(username);
  }

    public Employee update(Integer id, Employee employee) {
        getEmployeeById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }
    
    
        public Employee delete(Integer id) {
            Employee employee =getEmployeeById(id);
            employeeRepository.delete(employee);
            return employee;
        }


        

        }
        
    


        
            
