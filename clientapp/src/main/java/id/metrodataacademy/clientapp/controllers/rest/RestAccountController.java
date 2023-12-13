package id.metrodataacademy.clientapp.controllers.rest;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.metrodataacademy.clientapp.models.Employee;
import id.metrodataacademy.clientapp.services.AccountService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping ("/profile")
public class RestAccountController {

    private AccountService accountService;

    @GetMapping
    public Employee getProfile (Authentication auth){
        return accountService.getProfile(auth.getName());
    }
    
}
