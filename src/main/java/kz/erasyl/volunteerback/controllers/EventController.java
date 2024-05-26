package kz.erasyl.volunteerback.controllers;


import jakarta.transaction.Transactional;
import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.services.EventService;
import kz.erasyl.volunteerback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

//    @PostMapping("/add-volunteer")
//    public ResponseEntity<?> addVolunteer(@RequestParam(name = "volunteerId") Long volunteerId, @RequestParam(name = "eventId") Long eventId) {
//        return ResponseEntity.ok(eventService.addVolunteerToEvent(eventId, volunteerId));
//    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/username/{username}")
    @Transactional
    public ResponseEntity<?> getUsersAllEvents(@PathVariable String username) {
        System.out.println("Works ");
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PostMapping("/guest")
    public ResponseEntity<?> createEventByGuest(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEventByGuest(event));
    }

    @GetMapping("/organization/{id}")
    public ResponseEntity<?> getEventsByOrganization(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventsByOrganizationId(id));
    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<?> deactivateEventById(@PathVariable Long eventId){
        return ResponseEntity.ok(eventService.deactivateEventById(eventId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<?> activateEventById(@PathVariable Long eventId){
        return ResponseEntity.ok(eventService.activeEventById(eventId));
    }


}
