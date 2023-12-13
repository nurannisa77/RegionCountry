package id.metrodataacademy.serverapp.services;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;
import id.metrodataacademy.serverapp.dto.CountryDTO;
import id.metrodataacademy.serverapp.models.Country;
import id.metrodataacademy.serverapp.models.Region;
import id.metrodataacademy.serverapp.repositories.CountryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;
    

    public List<Country> getAll(){
        return countryRepository.findAll();
    }

    // without DTO
    public Country create (Country country) {
        return countryRepository.save(country);
    }

    // with DTO
    public Country createDTO (CountryDTO countryDTO){
        Country country = new Country();
        country.setCode(countryDTO.getCode());
        country.setName(countryDTO.getName());
        Region region = regionService.getById(countryDTO.getRegionId());
        country.setRegion(region);
        return countryRepository.save(country);
    }

    //DTO Model Mapper
    public Country createDTOByModelMapper(CountryDTO countryDTO) {
    Country country = modelMapper.map(countryDTO, Country.class);
    country.setRegion(regionService.getById(countryDTO.getRegionId()));
    return countryRepository.save(country);
  }

  
    public Country getById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country Not Found!"));
    }
    
    public void countryExists(Integer id) {
        if (!countryRepository.existsById(id)){
            throw new EntityNotFoundException("Country dengan ID " + id + " tidak ada");
        }
    }

    public Country update(Integer id, Country country){
        getById(id);
        country.setId(id);
        return countryRepository.save(country);
    }

    
    public Country delete (Integer id){
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }


    
}
