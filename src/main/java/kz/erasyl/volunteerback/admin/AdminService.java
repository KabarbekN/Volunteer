package kz.erasyl.volunteerback.admin;


import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.services.EventService;
import kz.erasyl.volunteerback.services.OrganizationService;
import kz.erasyl.volunteerback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrganizationService organizationService;
    private final UserService userService;
    private final EventService eventService;

    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}
