package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDetailsDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.model.UserPrincipal;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ImageService service;

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userRepository.findUserByUsername(name).orElseThrow();
    }


    public void updatePassword(NewPasswordDto newPasswordDto) {
        User user = getUser();
        user.setPassword(encoder.encode(newPasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    public void updateUser(UpdateUserDto updateUser) {
        User user = getUser();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());
        userRepository.save(user);
    }

    public void updateUserImage(MultipartFile file) throws IOException {
        User user = getUser();
        user.setImagePath(service.uploadUserImage(file));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow();
        UserDetailsDto userDetailsDto = new UserDetailsDto(user.getUsername(), user.getPassword(), user.getId(), user.getRole());
        return new UserPrincipal(userDetailsDto);
    }

//    public boolean userExists (String username ){
//        userRepository.findUserByUsername(username).isPresent();
//        return true;
//    }
}