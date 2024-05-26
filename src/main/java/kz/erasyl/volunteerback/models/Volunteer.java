package kz.erasyl.volunteerback.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import kz.erasyl.volunteerback.models.enums.City;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "volunteer")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long volunteerId;

    @OneToOne(fetch = FetchType.EAGER)

    private User user;


    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @Enumerated(EnumType.STRING)
    private City city;
    private String aboutMe;

    private LocalDate birthday;
    private String gender;
    private Integer experienceMonth;
    private Float rating;
    private Integer numberOfRates;

    @OneToMany(mappedBy = "volunteer")
    @JsonIgnore
    Set<VolunteerEventRegistration> registrations;

//        @ManyToMany(mappedBy = "volunteers")
//        @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "volunteerId")
//
//        private List<Event> events;



}
