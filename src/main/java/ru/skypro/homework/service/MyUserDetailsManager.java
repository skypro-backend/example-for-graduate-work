package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.MyUserDetails;
import ru.skypro.homework.exception.notFound.UserNotFoundException;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

@Service
public class MyUserDetailsManager implements UserDetailsManager {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public MyUserDetailsManager( PasswordEncoder passwordEncoder, UserRepository userRepository ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void createUser( UserDetails user ) {
        UserModel userModel = ((MyUserDetails) user).toModel();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);

    }

    @Override
    public void updateUser( UserDetails user ) {
        UserModel userModel = (UserModel) user;
        passwordEncoder.encode(userModel.getPassword());
        userRepository.save(userModel);
    }

    @Override
    public void deleteUser( String username ) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public void changePassword( String oldPassword, String newPassword ) {

    }

    @Override
    public boolean userExists( String username ) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public MyUserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        UserModel founded = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return MyUserDetails.fromModel(founded);
    }
}
