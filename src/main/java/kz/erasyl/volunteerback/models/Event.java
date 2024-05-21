package kz.erasyl.volunteerback.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kz.erasyl.volunteerback.models.enums.EventStatus;
import kz.erasyl.volunteerback.models.enums.EventType;
import lombok.*;

import java.util.List;

@Data
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
    private Organization organization;

    private String eventName;

    private String eventDescription;

    private String eventLocation;

    private Long eventStartDate;
    private Long eventEndDate;

    private String city;
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @ManyToMany
    private List<User> volunteers;
}
