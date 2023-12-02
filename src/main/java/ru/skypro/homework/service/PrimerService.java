package ru.skypro.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

@Service
public class PrimerService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public PrimerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findUser (String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean userExists(String userName) {
        return userRepository.existsUserByUsername(userName);
    }
    public void updatePassword (User user) {
        userRepository.save(user);
    }
    public void createUser(User build) {
        userRepository.save(build);
    }


}

