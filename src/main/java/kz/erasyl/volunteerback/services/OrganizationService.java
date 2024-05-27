package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.models.Event;
import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.models.User;
import kz.erasyl.volunteerback.models.Volunteer;
import kz.erasyl.volunteerback.models.enums.Role;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import kz.erasyl.volunteerback.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public Organization findOrganizationById(Long id) {
        return organizationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Organization not found")
        );
    }

    public Organization createOrganization(Organization request) {

//        if (userRepository.findById(
//                request.getOwner().getUserId()).get()
//                .getRole().equals(Role.ORGANIZATION
//        )){
//
//        }
        Organization organization = new Organization();
        organization.setName(request.getName());
        organization.setDescription(request.getDescription());
        organization.setAddress(request.getAddress());
        organization.setPhone(request.getPhone());
        organization.setEmail(request.getEmail());
        organization.setOwner(userRepository.findById(request.getOwner().getUserId()).get());
        organization.setBin(request.getBin());
        organization.setCity(request.getCity());
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(Organization request) {
        if (organizationRepository.findById(request.getOrganizationId()).isPresent()){
            Organization organization = organizationRepository.findById(request.getOrganizationId()).orElseThrow(
                    () -> new IllegalArgumentException("Organization not found")
            );
            organization.setName(request.getName());
            organization.setDescription(request.getDescription());
            organization.setAddress(request.getAddress());
            organization.setPhone(request.getPhone());
            organization.setEmail(request.getEmail());
//            organization.setOwner(request.getOwner());
            organization.setBin(request.getBin());
            organization.setCity(request.getCity());
            return organizationRepository.save(organization);
        }
        else{
            throw new IllegalArgumentException("Organization not found");
        }
    }




    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization getOrganizationByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return organizationRepository.findByOwner(user);
    }


}
