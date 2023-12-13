package id.metrodataacademy.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import id.metrodataacademy.clientapp.models.Employee;

@Service
public class AccountService {

    @Value("${server.base.url}/employee/profile")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    public Employee getProfile (String name){
        return restTemplate
       .exchange(
        url.concat("?name=" + name),
        HttpMethod.GET,
        null,
        Employee.class
      )
      .getBody();
  }
}