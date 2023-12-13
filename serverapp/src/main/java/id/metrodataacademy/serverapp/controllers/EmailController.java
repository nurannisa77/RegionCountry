package id.metrodataacademy.serverapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.thymeleaf.context.Context;

import id.metrodataacademy.serverapp.dto.Request.EmailRequest;
import id.metrodataacademy.serverapp.services.EmailService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private EmailService emailService;

    @PostMapping("/simple")
    public EmailRequest sendSimpleMessage(
        @RequestBody EmailRequest emailRequest){
        return emailService.sendSimpleMessage(emailRequest);
    }

    @PostMapping("/attach")
    public EmailRequest sendMessageWithAttachment(
        @RequestBody EmailRequest emailRequest){
        return emailService.sendMessageWithAttachment(emailRequest);
    }
    
    @PostMapping("/send-template-email")
    public ResponseEntity<String> sendTemplateEmail(@RequestBody EmailRequest emailRequest){
    emailService.sendTemplateEmail(emailRequest);
    return ResponseEntity.ok("Email sent successfully");
}

}

    
