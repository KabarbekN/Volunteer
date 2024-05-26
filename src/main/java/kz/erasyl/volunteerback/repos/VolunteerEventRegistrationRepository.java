package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.models.VolunteerEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerEventRegistrationRepository extends JpaRepository<VolunteerEventRegistration, Long> {

    @Query("select v from VolunteerEventRegistration v where v.volunteer = ?1 and v.event = ?2")
    Optional<VolunteerEventRegistration> findVolunteerEventRegistrationByVolunteerAndEvent(Volunteer volunteer, Event event);


    @Transactional
    @Modifying
    @Query("delete from VolunteerEventRegistration v where v.volunteer = ?1 and v.event = ?2")
    Integer deleteByVolunteerAndEvent(Volunteer volunteer, Event event);


    @Query("select v from VolunteerEventRegistration v where v.event = ?1")
    List<VolunteerEventRegistration> findByEvent(Event event);

    @Query("select v from VolunteerEventRegistration v where v.volunteer = ?1")
    List<VolunteerEventRegistration> findByVolunteer(Volunteer volunteer);
}
