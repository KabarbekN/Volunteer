package kz.erasyl.volunteerback.repos;

import kz.erasyl.volunteerback.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByConfirmationToken(String confirmationToken);
}
