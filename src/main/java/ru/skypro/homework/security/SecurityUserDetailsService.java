package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserPrincipal(user);
    }

    public boolean userExists(String userName) {
        User user = userRepository.findByEmail(userName);
        if (user == null) {
            return false;
        }
        return true;
    }

    public void createUser(Register register) {
        User newUser = new User()
                .setPhone(register.getPhone())
                .setRole(register.getRole())
                .setEmail(register.getUsername())
                .setFirstName(register.getFirstName())
                .setLastName(register.getLastName())
                .setPassword(register.getPassword());
        userRepository.save(newUser);
    }
}
