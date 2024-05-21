package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.repos.VolunteerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final UserService userService;

    public Volunteer getVolunteerByName(String username) {
        User user = userService.getUserByUsername(username);
         return  volunteerRepository.findById(user.getId()).orElse(null);
    }
}
