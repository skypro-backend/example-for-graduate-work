package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.UserMapper;
import ru.skypro.homework.service.UserService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.datical.liquibase.ext.init.InitProjectUtil.getExtension;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepo userRepo;
    UserMapper userMapper;

    UserDetailsManager userDetailsManager;

    @Value("${path.to.image.folder}")
    String path;


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
        userDetailsManager.changePassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());  //Я так понимаю, JDBC менеджер сам сохраняет
    }
    @PreAuthorize("isAuthenticated()")
    @Override
    public UpdateUser updUsr(UpdateUser updateUser) {
        int id = getMe().getId();
        UserEntity user = userRepo.getById(id);

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());

        userRepo.save(user);

        return updateUser;
    }

    @Override
    public void updPhoto(MultipartFile photo) throws IOException {
        UserEntity user = getMe();
        user.setImage(savePhoto(photo));
        if (user.getImage() == null) {
            throw new IllegalArgumentException("No new image provided. User is not saved");
        }
        userRepo.save(user);
    }

    public String savePhoto(MultipartFile photo) throws IOException{
        if(photo == null){
            return null;
        }
        UserEntity user = getMe();
        Path filePath = Path.of(path,user + "." + getExtension(photo.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = photo.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }

        return filePath.toString();
    }
}
