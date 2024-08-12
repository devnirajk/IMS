package com.InternalManagementSystem.IMS.helpers;

import com.InternalManagementSystem.IMS.entity.Organisation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/*
    UserDetailsService interface loads user specific data and return instance of UserDetails.
    UserDetails object represents the authenticated user in the Spring Security framework.


    Spring security does authentication on UserDetails obj and that's why we are creating custom class
    AuthOrganisationDetails implementing UserDetails.
 */


public class AuthOrganisationDetails extends Organisation implements UserDetails{


    private String username;    // admin username
    private String password;

    public AuthOrganisationDetails(Organisation organisation) {
        this.username = organisation.getAdminEmail();
        this.password = organisation.getAdminPassword();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
}