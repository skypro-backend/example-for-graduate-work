package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImagesRepository imagesRepository;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ImagesRepository imagesRepository) {
        this.userRepository = userRepository;
        this.imagesRepository = imagesRepository;
    }

    @Value("${path.to.avatars.folder}")
    private Path pathToAvatars;

    public User saveUsers(User user) {
        userRepository.save(user);
        return user;
    }

    public User findByUserName(String username) {
        return userRepository.findByEmail(username).orElseThrow(null);
    }

    @Override
    public UserDto saveUser(UserDto user) {
        logger.info("saving user");

        return null;
    }

    @Override
    public UserDto deleteUser(UserDto user) {
        logger.info("deleting user");
        return null;
    }

    @Override
    public UserDto getInfoUser() {
        return null;
    }

    @Override
    public UpdateUserDto setInfoUser(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public AvatarDto setAvatar(MultipartFile avatar) throws IOException {
        return null;
    }

    @Override
    public String saveToDisk(Long studentId, MultipartFile multipartFile) throws IOException {


        Files.createDirectories(pathToAvatars);
        String originalFilename = multipartFile.getOriginalFilename();
        int dotIndex = originalFilename.lastIndexOf(".");
        String extension = originalFilename.substring(dotIndex);
        String fileName = studentId + extension;
        String absolutePath = pathToAvatars.toAbsolutePath().toFile() + "/" + fileName;
        FileOutputStream fos = new FileOutputStream(absolutePath);
        multipartFile.getInputStream().transferTo(fos);
        fos.close();
        return absolutePath;
    }

    @Override
    public Images saveToDb(Integer userId, MultipartFile multipartFile, String absolutePath) throws IOException {
        User userReference = userRepository.getReferenceById(userId);
        //  User userReference = userRepository.getReferenceById(userId);
        // Student studentReference = studentRepository.getReferenceById(userId);
        Images images = imagesRepository.findByEmail(String.valueOf(userReference)).orElse(new Images());
        // Avatar avatar = avatarRepository.findByStudent(studentReference)
        //       .orElse(new Avatar());

        images.setUserId(userReference.getId());
        images.setFilePath(absolutePath);
        images.setMediaType(multipartFile.getContentType());
        images.setFileSize(multipartFile.getSize());
        images.setData(multipartFile.getBytes());
        imagesRepository.save(images);
        return images;
        // avatar.setStudent(studentReference);
        // avatar.setFilePath(absolutePath);
        //avatar.setMediaType(multipartFile.getContentType());
        //  avatar.setFileSize(multipartFile.getSize());
        // avatar.setData(multipartFile.getBytes());
        //avatarRepository.save(avatar);
        //return avatar;
    }


}
