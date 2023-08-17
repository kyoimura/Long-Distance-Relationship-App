package com.kyo.vehicleApp.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
//this class has the information about the user.
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    private String verificationCode;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, String verificationCode, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.verificationCode = verificationCode;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getVerificationCode() {return verificationCode;}
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
