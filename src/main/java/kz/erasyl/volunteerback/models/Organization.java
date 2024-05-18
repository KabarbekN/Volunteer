package kz.erasyl.volunteerback.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
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

    @OneToOne
    private User owner;

    private String name;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String bin;

//    @OneToMany(mappedBy = "organization")
//    private List<Event> events;

}
