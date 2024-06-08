package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
    @Query("select v from Volunteer v where v.aboutMe = ?1")
    Volunteer findByAboutMe(String aboutMe);


    @Query("select v from Volunteer v where v.user = ?1")
    Volunteer findByUser(User user);



}
