package kz.erasyl.volunteerback.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kz.erasyl.volunteerback.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    @SneakyThrows
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new ServiceException("Username already in use, please try another username, or log in by existing");
        }
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @GetMapping("/confirm")
    public RedirectView confirmMessage(@RequestParam String token) {
        boolean isConfirmed = authenticationService.confirmEmail(token);

        RedirectView redirectView = new RedirectView();
        if (isConfirmed) {
            redirectView.setUrl("http://localhost:4200/confirmation");  // Redirect to success page
        } else {
            redirectView.setUrl("http://localhost:4200/events");    // Redirect to error page
        }
        return redirectView;
    }
}
