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
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.UserUpdateReq;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUser() throws UserNotFoundException {
        User user = getAuthUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        return userToUserDTO(user);
    }


    @Override
    public User getUser(int userId) {
        try {
            return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(RegisterReq registerReq) {
        User user = fromRegisterReq(registerReq);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(int userId) {
        try {
            return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }

    /**
     * Обновление данных о пользователе
     */
    @Override
    public void updateUser(UserUpdateReq req) throws UserNotFoundException {
        User user = getAuthUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPhone(req.getPhone());
        userRepository.save(user);
    }
    /**
     * Получение информации о авторизованном пользователе
     */
    @Override
    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findUserByUserName(currentPrincipalName);
    }

    public UserDTO userToUserDTO(User user) {
        if (user.getImage() != null) {
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getPhone(),
                               user.getUserName(), "/user/photo/" + user.getId());
        } else {
            return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getPhone(),
                               user.getUserName(), "/user/photo/" + user.getId());
        }
    }

    /**
     * Обновление пароля пользователя
     */
    @Transactional
    @Override
    public boolean updateUserPassword(NewPassword passwordDTO) {
        if (isNotEmptyAndNotNull(passwordDTO.getNewPassword()) && isNotEmptyAndNotNull(
                passwordDTO.getCurrentPassword()))
        {
            User user = getAuthUser();
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private boolean isNotEmptyAndNotNull(String str) {
        return !(str == null || str.isEmpty() || str.isBlank());
    }

    private User fromRegisterReq(RegisterReq regreq) {
        User user = new User(regreq.getUsername(), regreq.getPassword(), regreq.getFirstName(), regreq.getLastName(),
                             regreq.getPhone(), regreq.getRole(), null);
        return user;
    }

    @Override
    public boolean userExists(String userName) {
        return userRepository.findUserByUserName(userName) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        UserDetails userSpring = org.springframework.security.core.userdetails.User.builder()
                                                                                   .username(user.getUserName())
                                                                                   .password(user.getPassword())
                                                                                   .roles(user.getRole().name())
                                                                                   .build();
        return userSpring;
    }

    @Override
    public boolean updateUserImage(MultipartFile file) throws UserNotFoundException {
        User user = getAuthUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        imageService.updateUserImage(user.getUserName(), file);
        return true;
    }

}
