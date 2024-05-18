package kz.erasyl.volunteerback.models;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class CreateOrganizationDTO {
    private User owner;

    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String bin;

    private List<Event> events;
}
