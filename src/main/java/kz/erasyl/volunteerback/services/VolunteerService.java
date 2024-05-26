package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.exceptions.OperationNotPermittedException;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.repos.VolunteerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final UserService userService;


    public Volunteer getVolunteerByName(String username) {
        User user = userService.getUserByUsername(username);
         return  volunteerRepository.findById(user.getUserId()).orElse(null);
    }
//    @SneakyThrows
    public Volunteer getVolunteerById(Long volunteerId) {
        return volunteerRepository.findById(volunteerId).orElseThrow(
                () -> new OperationNotPermittedException("Volunteer by id: " + volunteerId + " not found")
        );
    }

    public Volunteer getVolunteerByUserId(Long userId) {
        return volunteerRepository.findByUser(userService.getUserById(userId));
    }

    public Volunteer updateVolunteer(Volunteer volunteer) {
        System.out.println(volunteer.toString());
        User user = userService.getUserByUsername(volunteer.getUser().getUsername());

        volunteer.setUser(user);

        System.out.println(volunteer.toString());
        return volunteerRepository.save(volunteer);
//        return new Volunteer();
    }

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }
}
