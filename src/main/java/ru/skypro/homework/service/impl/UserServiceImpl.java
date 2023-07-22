package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    @Override
    public User getUser(int userId)  {
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

        try {
            User user = userRepository.findById(userDTO.getId()).orElseThrow(UserNotFoundException::new);
            user.setFirstName(userDTO.getFirstName());
            user.setLastName((userDTO.getLastName()));
            user.setMail(userDTO.getMail());
            user.setPhone(userDTO.getPhone());
            userRepository.saveAndFlush(user);

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private User fromRegisterReq(RegisterReq regreq) {
        User user = new User(regreq.getUsername(),
                regreq.getPassword(),
                regreq.getFirstName(),
                regreq.getLastName(),
                regreq.getPhone(),
                regreq.getRole());
        return user;
    }

    @Override
    public boolean userExists(String userName) {
        return userRepository.findUserByUserName(userName)!=null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        UserDetails userSpring =
                org.springframework.security.core.userdetails.User.builder()
                                                                  .username(user.getUserName())
                                                                  .password(user.getPassword())
                                                                  .roles(user.getRole().name())
                                                                  .build();
        return userSpring;
    }

}
