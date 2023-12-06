package ru.skypro.homework.config.userDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BackEndUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Method loadUserByUsername invoked!");
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        List<SimpleGrantedAuthority> grantedAuthorities= List.of(new SimpleGrantedAuthority(user.getRole().getCode()));

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
    }
}
