package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdate;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final ImageService imageService;


    @Override
    public Collection<UserDetails> getAllUserDetails() {

        List<UserDetails> userDetails = new ArrayList<>();
        List<User> users = usersRepository.findAll();

        users.forEach(user -> {

            userDetails.add(org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build());

        });


        return userDetails;
    }

    @Override
    public void registerNewUser(Register register, String encodePassword) {

        User user = new User();
        user.setEmail(register.getUsername());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPassword(encodePassword);
        user.setPhone(register.getPhone());
        user.setRegDate(LocalDate.now());
        user.setRole(register.getRole());

        usersRepository.save(user);

    }

    @Override
    public void updatePassword(User user) {
        usersRepository.save(user);
    }

    @Override
    public Optional<UserDto> getUserByDtoByLogin(String userLogin) {

        Optional<User> userOptional = usersRepository.findUserByEmail(userLogin);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        return Optional.ofNullable(UserMapper.INSTANCE.userToDto(user));

    }

    @Override
    public Optional<UserUpdate> updateUserByUserUpdate(String userLogin, UserUpdate userUpdate) {

        Optional<User> userOptional = usersRepository.findUserByEmail(userLogin);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        user = UserMapper.INSTANCE.userUpdateToUser(userUpdate, user);
        usersRepository.save(user);

        return Optional.of(userUpdate);

    }

    @Override
    public Optional<User> getUserByLogin(String userLogin) {
        return usersRepository.findUserByEmail(userLogin);
    }

    @Override
    public Optional<User> changeImage(String userLogin, MultipartFile multipartFile) {

        Optional<User> optionalUser = usersRepository.findUserByEmail(userLogin);

        if (optionalUser.isEmpty() || multipartFile.isEmpty() || multipartFile.getOriginalFilename() == null) {
            return Optional.empty();
        }

        User user = optionalUser.get();
        Image image;

        try {

            if (user.getImage() == null) {
                image = imageService.changeUserImage(user.getId(), -1L, multipartFile);
            } else {
                image = imageService.changeUserImage(user.getId(), user.getImage().getId(), multipartFile);
            }

            user.setImage(image);
            usersRepository.save(user);
            return Optional.of(user);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
