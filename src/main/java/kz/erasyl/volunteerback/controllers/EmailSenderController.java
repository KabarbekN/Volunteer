package kz.erasyl.volunteerback.controllers;

import kz.erasyl.volunteerback.requests.ContactUsRequest;
import kz.erasyl.volunteerback.services.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailSenderController {
    private final EmailSenderService emailSenderService;


    @PostMapping
    public ResponseEntity<?> sendEmail(@RequestBody ContactUsRequest request) {

        return ResponseEntity.ok(emailSenderService.contactUsEmail(request));
    }
}
