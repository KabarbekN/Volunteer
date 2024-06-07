package kz.erasyl.volunteerback.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "volunteer_organization_rating")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerOrganizationRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteerId")
    private Volunteer volunteer;
    @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;

    private Integer volunteerRating;

    private Integer organizationRating;

    private String volunteerFeedback;

    private String organizationFeedback;
}
