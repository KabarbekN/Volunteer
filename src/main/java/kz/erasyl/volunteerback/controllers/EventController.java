package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PostMapping("/add-volunteer")
    public ResponseEntity<?> addVolunteer(@RequestParam(name = "volunteerId") Long volunteerId, @RequestParam(name = "eventId") Long eventId) {
        return ResponseEntity.ok(eventService.addVolunteerToEvent(eventId, volunteerId));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

}
