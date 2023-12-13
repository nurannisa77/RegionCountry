package id.metrodataacademy.serverapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.metrodataacademy.serverapp.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional <User> findByUsernameOrEmployeeEmail(String username, String email); 
    
@Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
public List<User> findUserByUsername(@Param("username") String username);
}

