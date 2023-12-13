package id.metrodataacademy.serverapp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private Integer Id;
    private String name;
    private List<CountryDTO> countries;

}
    
   


