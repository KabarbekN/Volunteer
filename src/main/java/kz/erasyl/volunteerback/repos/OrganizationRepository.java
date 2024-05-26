package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query("select o from Organization o where o.owner = ?1")
    Organization findByOwner(User owner);
}
