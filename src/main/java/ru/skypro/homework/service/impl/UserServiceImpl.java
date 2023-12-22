package ru.skypro.homework.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.UserMapper;
import ru.skypro.homework.service.UserService;

public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    UserMapper userMapper;


    public UserEntity getMe() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByLogin(user.getUsername());
    }

    @Override
    public User getMeDTO() {
        return userMapper.UserEntityToDTO(getMe());
    }

    @Override
    public void updPass(NewPassword newPassword) {


    }

    @Override
    public UpdateUser updUsr(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updPhoto(MultipartFile photo) {

    }
}
