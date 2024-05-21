package kz.erasyl.volunteerback.services;


import kz.erasyl.volunteerback.models.Organization;
import kz.erasyl.volunteerback.repos.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public Organization findOrganizationById(Long id) {
        return organizationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Organization not found")
        );
    }

    public Organization createOrganization(Organization request) {
        Organization organization = new Organization();
        organization.setName(request.getName());
        organization.setDescription(request.getDescription());
        organization.setAddress(request.getAddress());
        organization.setPhone(request.getPhone());
        organization.setEmail(request.getEmail());
        organization.setOwner(request.getOwner());
        organization.setBin(request.getBin());
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
            organization.setOwner(request.getOwner());
            organization.setBin(request.getBin());
            return organizationRepository.save(organization);
        }
        else{
            throw new IllegalArgumentException("Organization not found");
        }
    }


    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}
