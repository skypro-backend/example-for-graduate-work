package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.dto.user.MyUserDetails;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsManager implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void createUser( RegisterReq registerReq ) {
        UserModel newUser = new UserModel();
        newUser.setUsername(registerReq.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        newUser.setRole(Role.USER);
        newUser.setFirstName(registerReq.getFirstName());
        newUser.setLastName(registerReq.getLastName());
        newUser.setPhone(registerReq.getPhone());
        userRepository.save(newUser);

    }
    public boolean userExists( String username ) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public MyUserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
