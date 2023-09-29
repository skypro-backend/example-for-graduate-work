package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.MyDatabaseUserDetails;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MyDatabaseUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new MyDatabaseUserDetails(userEntity);
    }

    @Transactional
    public void createUser(UserDetails myDatabaseUserDetails) {
        UserEntity userEntity = ((MyDatabaseUserDetails) myDatabaseUserDetails).getUserEntity();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    @Transactional
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Transactional
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyDatabaseUserDetails myDatabaseUserDetails = (MyDatabaseUserDetails) loadUserByUsername(authentication.getName());
        return myDatabaseUserDetails.toUserEntity();
    }
}
