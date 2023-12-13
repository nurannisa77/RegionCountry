package id.metrodataacademy.serverapp.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import id.metrodataacademy.serverapp.models.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
     
    // Native
    @Query(
        value = "SELECT * FROM region r WHERE r.region_name = ?1",
        nativeQuery = true
    )
    public List<Region> findByAllNameNative(String name);

    boolean existsByName(String name);
    //  JPQL
    @Query("SELECT r FROM Region r WHERE r.name = :name") 
    public List<Region> findByAllNameJPQL(@Param("name") String name);

    // Query Method
    public Optional<Region> findByName(String name);
}
    


    
    


