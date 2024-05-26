package kz.erasyl.volunteerback.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import kz.erasyl.volunteerback.models.enums.City;
import kz.erasyl.volunteerback.models.enums.EventStatus;
import kz.erasyl.volunteerback.models.enums.EventType;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private String eventName;

    private String eventDescription;

    private String eventLocation;

    private Long eventStartDate;
    private Long eventEndDate;

    @Enumerated(EnumType.STRING)
    private City city;
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    private Boolean active;

    private String link;

    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private Set<VolunteerEventRegistration> registrations;

//    @ManyToMany
//    @JoinTable(
//            name = "events_volunteers",
//            joinColumns = @JoinColumn(name = "event_id"),
//            inverseJoinColumns = @JoinColumn(name = "volunteer_id"))
//    @JsonBackReference
//    private List<Volunteer> volunteers;
    @Column(name = "is_approved")
    private boolean isApproved;
}
