package kz.erasyl.volunteerback.services;

import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.enums.EventStatus;
import kz.erasyl.volunteerback.repos.EventRepository;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
//        LocalDate.of(request.getEventStartDate().toString());

        event.setEventStartDate(request.getEventStartDate());
        event.setEventEndDate(request.getEventEndDate());
        event.setOrganization(request.getOrganization());
        event.setEventStatus(EventStatus.OPEN);
        event.setCity(request.getCity());
        event.setApproved(true);
        event.setLink(request.getLink());
        event.setActive(true);

        // todo
//        if (!request.getVolunteers().isEmpty()){
//            event.setVolunteers(request.getVolunteers());
//        }

        return eventRepository.save(event);
    }

    public List<Event> getEventsByOrganizationId(Long organizationId) {
        Organization organization = organizationService.findOrganizationById(organizationId);

        return eventRepository.findByOrganization(organization);
    }




    public Event createEventByGuest(Event request) {
        System.out.println(request);
        Event event = new Event();

        event.setEventDescription(request.getEventDescription());
        event.setEventName(request.getEventName());
        event.setEventLocation(request.getEventLocation());
        event.setEventType(request.getEventType());
        event.setEventStatus(request.getEventStatus());
        event.setEventStartDate(request.getEventStartDate());

        event.setEventEndDate(request.getEventEndDate());

        event.setEventStatus(EventStatus.OPEN);

        event.setOrganization(organizationService.findOrganizationById(1L));

        event.setCity(request.getCity());
        System.out.println(event);

        // todo
//        if (!request.getVolunteers().isEmpty()){
//            event.setVolunteers(request.getVolunteers());
//        }

        return eventRepository.save(event);
    }


//    public Event addVolunteerToEvent(Long eventId, Long userId) {
//        User user = userService.getUserById(userId);
//        Event event = getEventById(eventId);
//        List<User> users = event.getVolunteers();
//        users.add(user);
//        event.setVolunteers(users);
//        return eventRepository.save(event);
//    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new RuntimeException("Event by id: " + eventId + " not found")
        );
    }

    public List<Event> getAllEvents(){
//        System.out.println("true");
        return eventRepository.findAll();
//    return null;
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional// Cron expression for midnight every day
    public void closeAllEventsThatAlreadyGone(){
       List<Event> events =  eventRepository.findAll();
       events.forEach(event -> {
           if (event.getEventEndDate() < System.currentTimeMillis()){
               event.setEventStatus(EventStatus.CLOSED);
           }
       });
        eventRepository.saveAll(events);
    }

    public Event deactivateEventById(Long eventId) {
        Event event = getEventById(eventId);
        event.setActive(false);
        return eventRepository.save(event);
    }

    public Event activeEventById(Long eventId) {
        Event event = getEventById(eventId);
        event.setActive(true);
        return eventRepository.save(event);
    }

//    public List<Event> getUsersAllEvents(String username) {
//        List<Event> events = new ArrayList<>();
//        System.out.println(getAllEvents());
//        getAllEvents().forEach(event -> {
//            event.getVolunteers().forEach(volunteer -> {
//                System.out.println(volunteer.getUsername()+ "    " + username);
//                if (volunteer.getUsername().equals(username)) {
//                    events.add(event);
//                }
//            });
//        });
//        for (Event event : events) {
//            System.out.println(event);
//        }
//        return events;
//    }
}
