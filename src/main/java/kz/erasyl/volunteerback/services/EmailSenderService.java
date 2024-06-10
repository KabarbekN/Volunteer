package kz.erasyl.volunteerback.services;

import kz.erasyl.volunteerback.requests.ContactUsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Value("${spring.mail.username}")
    private String username;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Message sended");
    }

    public Boolean contactUsEmail(ContactUsRequest request){
        executorService.submit(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(request.getEmail());
            message.setTo(username);
            message.setSubject(request.getName());
            message.setText(request.getMessage());
            mailSender.send(message);
            System.out.println("Message sended");
            return true;
        });
        return true;
    }

}
