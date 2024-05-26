package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.models.VolunteerEventRegistration;
import kz.erasyl.volunteerback.repos.VolunteerEventRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerEventRegistrationService {
    private final VolunteerEventRegistrationRepository volunteerEventRegistrationRepository;
    private final EventService eventService;
    private final VolunteerService volunteerService;
    private final UserService userService;

    public Boolean registrationToTheEvent(Long eventId, Long volunteerId) {

        if (eventService.getEventById(eventId) == null || volunteerService.getVolunteerById(volunteerId) == null) {
            return false;
        }
        VolunteerEventRegistration event = VolunteerEventRegistration.builder()
                .approved(false)
                .volunteer(volunteerService.getVolunteerById(volunteerId))
                .event(eventService.getEventById(eventId))
                .build();

        volunteerEventRegistrationRepository.save(event);
        return true;
    }

    public Boolean unregisterFromTheEvent(Long eventId, Long volunteerId) {
        if (checkIfRegistrationExist(eventId, volunteerId)){
            volunteerEventRegistrationRepository.deleteByVolunteerAndEvent(
                    volunteerService.getVolunteerById(volunteerId),
                    eventService.getEventById(eventId)
            );
            return true;
        }
        return false;
    }


    protected Boolean checkIfRegistrationExist(Long eventId, Long volunteerId) {
        if (eventService.getEventById(eventId) == null || volunteerService.getVolunteerById(volunteerId) == null) {
            return false;
        }
        return true;
    }

    public List<Event> getAllEventsByVolunteerId(Long volunteerId) {
       List<VolunteerEventRegistration> volunteerEventRegistrations =  volunteerEventRegistrationRepository.findByVolunteer(volunteerService.getVolunteerById(volunteerId));
       List<Event> events = new ArrayList<>();
       volunteerEventRegistrations.forEach(volunteerEventRegistration ->
               events.add(volunteerEventRegistration.getEvent())
               );
       return events;
    }

    public List<Volunteer> getAllVolunteersByEventId(Long eventId) {
        List<VolunteerEventRegistration> volunteerEventRegistrations = volunteerEventRegistrationRepository.findByEvent(eventService.getEventById(eventId));
        List<Volunteer> volunteers = new ArrayList<>();
        volunteerEventRegistrations.forEach(volunteerEventRegistration ->
                volunteers.add(volunteerEventRegistration.getVolunteer())
        );
        return volunteers;
    }

    public List<Event> getAllEventsByVolunteerUsername(String username) {
        Volunteer volunteer = volunteerService.getVolunteerByName(username);
        List<VolunteerEventRegistration> volunteerEventRegistrations =  volunteerEventRegistrationRepository.findByVolunteer(volunteer);
        List<Event> events = new ArrayList<>();
        volunteerEventRegistrations.forEach(volunteerEventRegistration ->
                events.add(volunteerEventRegistration.getEvent())
        );
        return events;
    }

}
