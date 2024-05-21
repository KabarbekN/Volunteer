package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.services.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;


    @GetMapping("/username")
    public ResponseEntity<?> getVolunteerByUsername(@RequestParam String username) {
        return ResponseEntity.ok(volunteerService.getVolunteerByName(username));
    }
}
