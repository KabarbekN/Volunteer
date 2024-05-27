package kz.erasyl.volunteerback.repos;


import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.models.VolunteerOrganizationRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerOrganizationRatingRepository extends JpaRepository<VolunteerOrganizationRating, Long> {
    @Query("select v from VolunteerOrganizationRating v where v.volunteer = ?1 and v.organization = ?2")
    List<VolunteerOrganizationRating> findAllByVolunteerAndOrganization(Volunteer volunteer, Organization organization);
    // should not work

    @Query("select v from VolunteerOrganizationRating v where v.volunteer = ?1")
    List<VolunteerOrganizationRating> findAllByVolunteer(Volunteer volunteer);

    @Query("select v from VolunteerOrganizationRating v where v.organization = ?1")
    List<VolunteerOrganizationRating> findAllByOrganization(Organization organization);

    @Query("select v from VolunteerOrganizationRating v where v.organization = ?1 and v.volunteer = ?2")
    Optional<VolunteerOrganizationRating> findByOrganizationAndVolunteer(Organization organization, Volunteer volunteer);
}
