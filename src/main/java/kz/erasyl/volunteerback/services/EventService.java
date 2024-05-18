package kz.erasyl.volunteerback.services;

import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.repos.EventRepository;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final OrganizationService organizationService;
    private final UserService userService;

    public Event createEvent(Event request) {
        Event event = new Event();

        event.setEventDescription(request.getEventDescription());
        event.setEventName(request.getEventName());
        event.setEventLocation(request.getEventLocation());
        event.setEventType(request.getEventType());
        event.setEventStatus(request.getEventStatus());
        event.setEventStartDate(request.getEventStartDate());
        event.setEventEndDate(request.getEventEndDate());
        event.setOrganization(request.getOrganization());
        if (!request.getVolunteers().isEmpty()){
            event.setVolunteers(request.getVolunteers());
        }
        return eventRepository.save(event);
    }

    public Event addVolunteerToEvent(Long eventId, Long userId) {
        User user = userService.getUserById(userId);
        Event event = getEventById(eventId);
        List<User > users = event.getVolunteers();
        users.add(user);
        event.setVolunteers(users);
        return eventRepository.save(event);
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new RuntimeException("Event by id: " + eventId + " not found")
        );
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

}
