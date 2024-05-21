package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

}
