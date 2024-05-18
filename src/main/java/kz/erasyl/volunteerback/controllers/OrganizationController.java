package kz.erasyl.volunteerback.controllers;


import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.services.OrganizationService;
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
}
