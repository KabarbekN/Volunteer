package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.eventDescription = ?1 and e.eventId = ?2")
    Event findByEventDescriptionAndAndEventId(String eventDescription, String eventId);

    @Query("select e from Event e where e.organization = ?1")
    List<Event> findByOrganization(Organization organization);
}
