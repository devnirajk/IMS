package com.InternalManagementSystem.IMS.service.serviceImplementation;

import com.InternalManagementSystem.IMS.entity.Organisation;
import com.InternalManagementSystem.IMS.helpers.AuthOrganisationDetails;
import com.InternalManagementSystem.IMS.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
    This class is responsible for loading the user in the form of userDetails object for auth.
 */


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    OrganisationRepository organisationRepository;

    @Override
    public UserDetails loadUserByUsername(String adminEmail) throws UsernameNotFoundException {
        Optional<Organisation> organisation = organisationRepository.findByAdminEmail(adminEmail);

        if(organisation.isPresent()){
            return new AuthOrganisationDetails(organisation.get());
        }else {
            throw new UsernameNotFoundException("User not found with email!!!");
        }
    }
}
