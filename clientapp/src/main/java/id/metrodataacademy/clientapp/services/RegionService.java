package id.metrodataacademy.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import id.metrodataacademy.clientapp.models.Region;



@Service
public class RegionService {

        @Value("${server.base.url}/region")
        private String url;

        @Autowired
        private RestTemplate restTemplate;
  
        

        public List<Region> getAll() {
                return restTemplate
                .exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Region>>() {})
                .getBody();
        }

        public Region create(Region region) {
                return restTemplate
                .exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(region),
                Region.class)
                .getBody();
        }

        public Region getById(Integer id) {
                return restTemplate
                .exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                Region.class, id)
                .getBody();
        }

        public Region update(Integer id, Region region) {
                return restTemplate
                .exchange(
                url.concat("/" + id),
                HttpMethod.PUT,
                new HttpEntity<>(region),
                Region.class)
                .getBody();
        }

        public Region delete(Integer id) {
                return restTemplate
                .exchange(
                url.concat("/" + id),
                HttpMethod.DELETE,
                null,
                Region.class, id)
                .getBody();
        }

        public List<Region> searchByName(String name) {
                return restTemplate
                .exchange(
                url.concat("/search?name=" + name),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Region>>() {}
                )
                .getBody();
              }
            }

