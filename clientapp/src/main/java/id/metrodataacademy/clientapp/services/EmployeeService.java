package id.metrodataacademy.clientapp.services;

import id.metrodataacademy.clientapp.models.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

  @Value("${server.base.url}/employee")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  public List<Employee> getAll() {
    return restTemplate
      .exchange(
        url,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Employee>>() {}
      )
      .getBody();
  }

  public Employee create(Employee employee) {
    return restTemplate
      .exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(employee),
        Employee.class
      )
      .getBody();
  }

  public Employee getById(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.GET, null, Employee.class)
      .getBody();
  }

  public Employee update(Integer id, Employee employee) {
    return restTemplate
      .exchange(
        url.concat("/" + id),
        HttpMethod.PUT,
        new HttpEntity<>(employee),
        Employee.class
      )
      .getBody();
  }

  public Employee delete(Integer id) {
    return restTemplate
      .exchange(url.concat("/" + id), HttpMethod.DELETE, null, Employee.class)
      .getBody();
  }
}