package id.metrodataacademy.serverapp.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import id.metrodataacademy.serverapp.models.Region;
import id.metrodataacademy.serverapp.services.RegionService;
import lombok.AllArgsConstructor;

//@Controller untuk mengelola tampilan web dan @RestController untuk menyediakan layanan API.
@RestController
@AllArgsConstructor
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class RegionController {
    private RegionService regionService;

    
    @GetMapping
    public List<Region> getAll(){
        return regionService.getAll();

    }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id){
        return regionService.getById(id);
    }
    
    @PostMapping
    public Region create(@RequestBody Region region){
        return regionService.create(region);

    }
    
    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region){
        return regionService.update(id, region);
    }
    
    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id){
        return regionService.delete(id);
    }

    // //Native
    // @GetMapping("/native")
    // public List<Region> searchByNameNative(
    //     @RequestParam(name = "name") String name
    // ){
    //     return regionService.searchByNameNative(name);
    // }

    //JPQL
    @GetMapping("/jpql")
    public List<Region> searchByNameJPQL(
        @RequestParam(name ="name") String name
    ) {
        return regionService.searchByNameJPQL(name);
    }
}
