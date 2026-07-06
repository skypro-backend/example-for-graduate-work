package ru.skypro.homework.service.impl;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.IUserService;
import ru.skypro.homework.user.NewPassword;
import ru.skypro.homework.user.UpdateUser;
import ru.skypro.homework.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final Environment env;

    public UserServiceImpl(
            UserRepository repo,
            UserMapper mapper,
            PasswordEncoder encoder,
            Environment env) {

        this.repo = repo;
        this.mapper = mapper;
        this.encoder = encoder;
        this.env = env;
    }

    @Override
    public void uploadAvatar(MultipartFile image, Authentication auth) {
        UserEntity user = getCurrentUserEntity(auth);
        String path = saveImage(image);
        user.setImageUrl(path);
        repo.save(user);
    }

    private String saveImage(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();

            String originalName = file.getOriginalFilename();
            String ext = Objects.requireNonNull(originalName).substring(originalName.lastIndexOf('.'));
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + ext;

            Path root = Paths.get(env.getProperty("app.images.root-path"));
            Files.createDirectories(root);
            Files.write(root.resolve(filename), bytes);

            return env.getProperty("app.images.url-prefix") + filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image", e);
        }
    }

    @Override
    public User getCurrentUser(Authentication auth) {
        String username = auth.getName();
        UserEntity entity = repo.findByUsername(username).orElseThrow();
        return mapper.entityToDto(entity);
    }

    @Override
    public User updateProfile(UpdateUser data, Authentication auth) {
        UserEntity entity = getCurrentUserEntity(auth);
        mapper.updateFromDto(data, entity);
        repo.save(entity);
        return mapper.entityToDto(entity);
    }

    @Override
    public Boolean changePassword(NewPassword data, Authentication auth) {
        UserEntity user = getCurrentUserEntity(auth);
        if (!encoder.matches(data.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Old password mismatch");
        }
        user.setPassword(encoder.encode(data.getNewPassword()));
        repo.save(user);
        return true;
    }

    @Override
    public Integer register(Register data) {
        UserEntity entity = mapper.dtoToEntity(data);
        entity.setPassword(encoder.encode(data.getPassword()));
        UserEntity saved = repo.save(entity);
        return saved.getId().intValue();
    }

    private UserEntity getCurrentUserEntity(Authentication auth) {
        String username = auth.getName();
        return repo.findByUsername(username).orElseThrow();
    }
}