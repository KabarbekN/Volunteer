package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select e from Event e where e.eventDescription = ?1 and e.eventId = ?2")
    Event findByEventDescriptionAndAndEventId(String eventDescription, String eventId);
}
