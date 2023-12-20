package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.MissingImageException;
import ru.skypro.homework.exceptions.PasswordNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.UsersService;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    private UserMapper userMapper;
    private final String imageDir;
    private UserEntityRepository userEntityRepository;

    public UsersServiceImpl(UserMapper userMapper
            , @Value("${path.to.images.folder}") String imageDir
            , UserEntityRepository userEntityRepository) {
        this.userMapper = userMapper;
        this.imageDir = imageDir;
        this.userEntityRepository = userEntityRepository;
    }

    //    @Value("${path.to.images.folder}") String imageDir
    @Override
    public void setPassword(NewPassword newPassword) {
        Optional<UserEntity> userEntity = userEntityRepository.findByPassword(newPassword.getCurrentPassword());
        if (newPassword.getCurrentPassword().equals(userEntity.get().getPassword())) {
            userEntity.get().setPassword(newPassword.getNewPassword());
            userEntityRepository.save(userEntity.get());
        } else {
            throw new PasswordNotFoundException("Введенный Вами пароль не найден!");
        }
    }
    @Override
    public User getUser() {

        // туДу - откуда получать информацию о айди пользователя?
        UserEntity userEntity = userEntityRepository.findById(1).get();
        if (userEntity.getId() == null ) {
            throw new EntityNotFoundException("Пользователь не найден");
        }
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        // туДу - откуда получать информацию о айди пользователя?
        UserEntity userEntity = userEntityRepository.findById(1).get();
                Optional < UpdateUser > updateUserOptional = Optional.ofNullable(updateUser);
        if (updateUserOptional.isEmpty()) {
            throw new EntityNotFoundException("Информация о пользователе отсутствует!");
        }
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getFirstName());
        userEntity.setPhone(updateUser.getPhone());

        userEntityRepository.save(userEntity);
        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image) {
        UserEntity userEntity = userEntityRepository.findById(1).get();
        if (!image.getContentType().equals("image/jpeg") || !image.getContentType().equals("image/png")) {
            throw new MissingImageException("Некорректный формат изображения пользователя!" );
        }
        // туДу заменить методом обработки фото
        Path filePath = Path.of(imageDir, userEntity.getPhone() + ".images");
        ImageEntity imageEntity = ImageEntity.builder()
                .filePath(filePath.toString())
                .mediaType(image.getContentType())
                .fileSize(image.getSize())
                .build();
        //
        userEntity.setImageEntity(imageEntity);
    }
}
