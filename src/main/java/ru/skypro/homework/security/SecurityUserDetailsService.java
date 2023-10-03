package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.mapper.UserMapper;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return new SecurityUserPrincipal(userRepository.findByEmail(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
    }

}
