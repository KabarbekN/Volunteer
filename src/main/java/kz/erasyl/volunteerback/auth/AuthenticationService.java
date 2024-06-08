package kz.erasyl.volunteerback.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.erasyl.volunteerback.configs.JwtService;
import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.models.enums.Role;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import kz.erasyl.volunteerback.repos.UserRepository;
import kz.erasyl.volunteerback.repos.VolunteerRepository;
import kz.erasyl.volunteerback.services.EmailSenderService;
import kz.erasyl.volunteerback.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ExecutorService executorService = Executors.newCachedThreadPool();


    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VolunteerRepository volunteerRepository;
    private final OrganizationRepository organizationRepository;
    private final EmailSenderService emailSenderService;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request){
        String confirmationToken = UUID.randomUUID().toString();
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .confirmationToken(confirmationToken)
                .isEmailVerified(false)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        sendConfirmationMessage(request.getEmail(), confirmationToken);


        if (request.getRole() == Role.VOLUNTEER){
            volunteerRepository.save(Volunteer.builder().user(savedUser).email(request.getEmail()).numberOfRates(0).rating(5F).build());
        }
        if (request.getRole() == Role.ORGANIZATION){
            organizationRepository.save(Organization.builder().owner(savedUser).numberOfRates(0).rating(5F).build());
        }

//        volunteerRepository.save(Volunteer.builder().user(savedUser).build());

        log.info(savedUser.getUsername() + ": registered");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String username = request.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        log.info(username + " was authenticated");
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.contains("Bearer ")){
            return;
        }

        refreshToken = authHeader.substring(7);

        username = jwtService.extractUsername(refreshToken);

        if (username != null){
            var user = this
                    .userRepository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)){
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public void sendConfirmationMessage(String email, String token) {
                executorService.submit(() -> {

                    String subject = "Confirmation Message";
                    String confirmationUrl = "http://localhost:8080/api/v1/auth/confirm?token=" + token;
                    String body = "Please click the following link to confirm your email: " + confirmationUrl;

                    emailSenderService.sendEmail(email, subject, body);
                });
    }

    public boolean confirmEmail(String token){
//        executorService.submit(() -> {
            User user = userService.findUserByConfirmationToken(token);
            if (user != null){
                user.setConfirmationToken(null);
                user.setEmailVerified(true);
                userRepository.save(user);
                return true;
            }
            return false;
//        });
    }
}
