package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.services.OrganizationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(organizationService.createOrganization(organization));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.findOrganizationById(id));
    }

    @PutMapping
    public ResponseEntity<?> updateOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(organizationService.updateOrganization(organization));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getOrganizationByUsername(@PathVariable String username) {
        return ResponseEntity.ok(organizationService.getOrganizationByUsername(username));
    }

}
