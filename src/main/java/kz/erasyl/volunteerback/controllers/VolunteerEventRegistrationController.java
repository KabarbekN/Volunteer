package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.services.VolunteerEventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event-registration")
@RequiredArgsConstructor
public class VolunteerEventRegistrationController {
    private final VolunteerEventRegistrationService eventRegistrationService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam(name = "eventId") Long eventId, @RequestParam(name = "volunteerId") Long volunteerId) {
        return ResponseEntity.ok(eventRegistrationService.registrationToTheEvent(eventId, volunteerId));
    }

    @DeleteMapping("/unregister")
    public ResponseEntity<?> unregister(@RequestParam(name = "eventId") Long eventId, @RequestParam(name = "volunteerId") Long volunteerId) {
        return ResponseEntity.ok(eventRegistrationService.unregisterFromTheEvent(eventId, volunteerId));
    }

    @GetMapping("/volunteer/{id}")
    public ResponseEntity<?> getVolunteersEventsById(@PathVariable Long id) {
        return ResponseEntity.ok(eventRegistrationService.getAllEventsByVolunteerId(id));
    }
    @GetMapping("/event/username/{username}")
    public ResponseEntity<?> getEventsByVolunteerUsername(@PathVariable String username) {
        return ResponseEntity.ok(eventRegistrationService.getAllEventsByVolunteerUsername(username));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<?> getEventsByVolunteerId(@PathVariable Long id) {
        return ResponseEntity.ok(eventRegistrationService.getAllVolunteersByEventId(id));
    }

    @GetMapping("/organization/volunteer/{username}")
    public ResponseEntity<?> getAppliedOrganizationsOfVolunteer(@PathVariable String username) {
        return ResponseEntity.ok(eventRegistrationService.getAllOrganizationsOfVolunteer(username));
    }


}
