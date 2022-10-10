package com.services;

import com.models.Person;
import com.repositories.PersonRepository;
import com.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsServiceImpl")
//@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> byUsername = personRepository.findByUsername(username);
        System.out.println(byUsername.get());
        if (byUsername == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(byUsername.get());
    }
}
