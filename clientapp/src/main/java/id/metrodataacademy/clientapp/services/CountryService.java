package id.metrodataacademy.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.metrodataacademy.clientapp.models.Country;

@Service
public class CountryService {

    @Value("${server.base.url}/country")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public List<Country> getAll() {
        return restTemplate
        .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Country>>() {})
        .getBody();
    }

    public Country getById(Integer id) {
        return restTemplate
        .exchange(
        // url.concat("/" + id),
        url + "/" + id,
        HttpMethod.GET,
        null,
        Country.class)
        .getBody();
    }

    public Country create(Country Country) {
        return restTemplate
        .exchange
        (url, 
        HttpMethod.POST, 
        new HttpEntity<>(Country), 
        Country.class)
        .getBody();
    }

    public Country update(Integer id, Country Country) {
        return restTemplate
        .exchange(
         url.concat("/" + id),
         HttpMethod.PUT,
         new HttpEntity<>(Country),
         Country.class)
         .getBody();
    }

    public Country delete(Integer id) {
        return restTemplate
        .exchange(url.concat("/" + id), 
        HttpMethod.DELETE, 
        null, 
        Country.class)
        .getBody();
    }

    public List<Country> searchByNameJPQL(String name) {
        return restTemplate
        .exchange(
        url.concat("/search?name=" + name),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Country>>() {}
        )
        .getBody();
      }
    }


