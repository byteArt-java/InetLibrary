package com.security;


import com.models.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {
    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(person.getRole()));
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !person.getBanned();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !person.getBanned();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !person.getBanned();
    }

    @Override
    public boolean isEnabled() {
        return !person.getBanned();
    }
}
