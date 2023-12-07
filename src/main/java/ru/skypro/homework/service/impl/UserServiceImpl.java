package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public ResponseEntity<String> setPassword(NewPassword newPassword, UserDetails userDetails) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDetails.getUsername());
        if(userEntityOptional.isPresent()) {
            UserEntity userEntityUpdatedPassword = userMapper.newPasswordDTOToUserEntity(newPassword);
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntityUpdatedPassword.getPassword()));
            userRepository.save(userEntity);
            return new ResponseEntity<>("Пароль изменен", HttpStatus.OK);
        } else return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<User> getUser(UserDetails userDetails) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userEntityOptional.isPresent()) {
            return new ResponseEntity<>(userMapper.userToUserDTO(userEntityOptional.get()), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<UpdateUser> updateUser(UpdateUser updateUser, UserDetails userDetails) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            userEntity.setFirstName(updateUser.getFirstName());
            userEntity.setLastName(updateUser.getLastName());
            userEntity.setPhone(updateUser.getPhone());
            userRepository.save(userEntity);
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
