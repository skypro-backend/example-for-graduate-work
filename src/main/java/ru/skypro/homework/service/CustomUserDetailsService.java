package ru.skypro.homework.service;

import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.component.SecurityUserPrincipal;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;


@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        if (userRepository.findByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException(username);}

        User user = userRepository.findByUsername(username).get();

        return new SecurityUserPrincipal(user);
    }

}

