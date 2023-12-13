package id.metrodataacademy.serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.metrodataacademy.serverapp.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Integer>{
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

// find employee by username
@Query("SELECT e FROM Employee e WHERE e.user.username = :username")
public Employee findEmployeeByUsername(@Param("username") String username);

  // JPQL search by name using name parameter
@Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
public List<Employee> findEmployeeByName(@Param("name") String name);
}


    


