package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.services.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;


    @GetMapping("/username")
    public ResponseEntity<?> getVolunteerByUsername(@RequestParam String username) {
        return ResponseEntity.ok(volunteerService.getVolunteerByName(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerService.getVolunteerById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVolunteerByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(volunteerService.getVolunteerByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<?> updateVolunteerByUsername(@RequestBody Volunteer volunteer) {
        return ResponseEntity.ok(volunteerService.updateVolunteer(volunteer));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAllVolunteers());
    }



}
