package kz.erasyl.volunteerback.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kz.erasyl.volunteerback.models.enums.City;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizationId")
    private Long organizationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private User owner;

    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String bin;
    @Enumerated(EnumType.STRING)
    private City city;
    private boolean isApproved;

    @OneToMany(mappedBy = "organization")
    @JsonIgnore
    private List<Event> events;

    private Float rating;
    private Integer numberOfRates;



//    @OneToMany(mappedBy = "organization")
//    private List<Event> events;

}
