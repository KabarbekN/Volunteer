package kz.erasyl.volunteerback.admin;


import kz.erasyl.volunteerback.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administration")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    @GetMapping("/organizations")
    public ResponseEntity<?> getAllOrganizations(){
        return ResponseEntity.ok(adminService.getAllOrganizations());
    }
    @GetMapping("/events")
    public ResponseEntity<?> getAllEvents(){
        return ResponseEntity.ok(adminService.getAllEvents());
    }

}
