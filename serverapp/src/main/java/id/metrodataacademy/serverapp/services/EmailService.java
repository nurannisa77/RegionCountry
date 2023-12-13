package id.metrodataacademy.serverapp.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import id.metrodataacademy.serverapp.dto.Request.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailRequest sendSimpleMessage(EmailRequest emailRequest){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequest.getTo());
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getText());
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailRequest;
    }

    public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText());

            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachment()));
            helper.addAttachment(file.getFilename(), file);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailRequest;
    }

    public EmailRequest sendTemplateEmail(EmailRequest emailRequest) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
    
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
    
            
            String email = emailRequest.getTo();
            String name = email.substring(0, email.indexOf('@'));
    
            Context context = new Context();
            context.setVariable("message", emailRequest.getText());
            context.setVariable("name", name);
            
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sentDate = dateFormat.format(new Date());
            context.setVariable("sentDate", sentDate);
    
            String htmlContent = templateEngine.process("Email.html", context);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emailRequest;
    }
}
