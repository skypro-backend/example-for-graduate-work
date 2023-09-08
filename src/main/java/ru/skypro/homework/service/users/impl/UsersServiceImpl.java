package ru.skypro.homework.service.users.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.dto.mappers.UserMapper;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCover;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.repository.users.UserCoverRepository;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.users.UsersService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private  UserMapper userMapper;

    private final UserCoverRepository userCoverRepository;

    public UsersServiceImpl(UsersRepository usersRepository, UserCoverRepository userCoverRepository) {
        this.usersRepository = usersRepository;

        this.userCoverRepository = userCoverRepository;
    }

    @Override
    @Transactional
    public void register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        usersRepository.save(user);
    }

    @Override
    public User addNewPassword(Integer id,NewPasswordDto newPasswordDto) {
        User user = usersRepository.findUserById(id);
        user.setPassword(newPasswordDto.getCurrentPassword());
        user.setPassword(newPasswordDto.getNewPassword());
        return usersRepository.save(user);
    }

    @Override
    public User getUser(Integer id) throws UserNotFoundEx {
        User user = usersRepository.findUserById(id);
        return user;
    }

    @Override
    public User updateUser(Integer id, UpdateUserDto userDto){
        User user = usersRepository.findUserById(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        return usersRepository.save(user);
    }

    @Value("${user.cover.dir.path}")
    private String fileDir;

    @Override
    public void uploadImage(Integer userId, MultipartFile multipartFile) throws IOException {
        User user = usersRepository.findUserById(userId);
        Path filePath = Path.of(fileDir, user + ".");// + getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = multipartFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        UserCover userCover = findCover(userId);
        userCover.setUser(user);
        userCover.setFilePath(filePath.toString());
        userCover.setMediaType(multipartFile.getContentType());
        userCover.setFileSize(String.valueOf(multipartFile.getSize()));
        userCover.setPreview(generateImagePreviev(filePath));
        userCoverRepository.save(userCover);

    }
    public  UserCover findCover(Integer id) {
        UserCover userId = userCoverRepository.findByUserId(id);
        if (userId == null) {
            return new UserCover();
        }
        return userId;
    }
    private byte[] generateImagePreviev(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()),baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
