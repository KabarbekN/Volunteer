package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.exceptions.OperationNotPermittedException;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.repos.UserRepository;
import kz.erasyl.volunteerback.requests.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User with username " + username + " not found")
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @SneakyThrows
    public void changePassword(
            ChangePasswordRequest changePasswordRequest,
            Principal connectedUser) {
        var user =  (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!passwordEncoder.matches(changePasswordRequest.getCurrentPassword(), user.getPassword())){
            throw new OperationNotPermittedException("Wrong Password");
        }

        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmationPassword())){
            throw new OperationNotPermittedException("Passwords are not same");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getConfirmationPassword()));
        log.info("users new password was saved");
        userRepository.save(user);

    }

}
