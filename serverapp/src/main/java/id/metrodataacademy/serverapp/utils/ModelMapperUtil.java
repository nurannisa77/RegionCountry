package id.metrodataacademy.serverapp.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import org.modelmapper.convention.MatchingStrategies;


@Configuration
public class ModelMapperUtil {
     @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper
      .getConfiguration()
      .setMatchingStrategy(MatchingStrategies.STRICT);
    return modelMapper;
  }

    

    
}

