package id.metrodataacademy.clientapp.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.clientapp.models.Country;
import id.metrodataacademy.clientapp.services.CountryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping ("/api/country")
public class RestCountryController {
    
    private CountryService CountryService;

    @GetMapping
    public List<Country> getAll(){
        return CountryService.getAll();
    }

    @PostMapping
    public Country create(@RequestBody Country Country) {
        return CountryService.create(Country);

    }
    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return CountryService.getById(id);

    }

    @PutMapping("{id}")
    public Country update(@PathVariable Integer id, @RequestBody Country Country) {
        return CountryService.update(id, Country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return CountryService.delete(id);
  }
}

