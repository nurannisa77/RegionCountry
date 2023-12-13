package id.metrodataacademy.serverapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.metrodataacademy.serverapp.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>{

    boolean existsByName(String name);
    boolean existsByCode(String code);

@Query("SELECT c FROM Country c WHERE c.name LIKE %:name%")
public List<Country> findCountryByName(@Param("name") String name);
}

    
    
