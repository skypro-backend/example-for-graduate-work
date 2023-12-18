package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService,UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageService imageService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }
    @Override
    public UserDTO getUserInfo() {
        Optional<User> currentUser = findAuthUser();
        UserDTO currentUserDTO = new UserDTO();
        if (currentUser.isPresent()) {
            currentUserDTO = userMapper.mapUserToUserDTO(currentUser.get());
        }
        return currentUserDTO;
    }

    @Override
    public UserDTO updateInfoUser(UserDTO userDTO) {
        Optional<User> currentUser = findAuthUser();
        User user = new User();
        if (currentUser.isPresent()) {
            user = currentUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhone(userDTO.getPhone());
            userRepository.save(user);
        }
        return userMapper.mapUserToUserDTO(user);
    }

    @Override
    public Optional<User> findAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();
        return userRepository.findUserByEmail(currentName);
    }

    @Override
    public void updateUserImage(MultipartFile image) {
        User user = findAuthUser().orElseThrow();
        Image oldImage = user.getImage();
        if (oldImage == null) {
            Image newImage = imageService.createImage(image);
            user.setImage(newImage);
        } else {
            Image updateImage = imageService.updateImage(image, oldImage);
            user.setImage(updateImage);
        }
        userRepository.save(user);
    }
}
