package ru.skypro.homework.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private Logger LoggerFactory;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(UserMapper.class));


    public UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage().getLink()
        );
    }

    public User mapToEntity(UserDTO userDTO) {
        return new ru.skypro.homework.model.User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userRepository.findById(userDTO.getId()).get().getPassword(),
                userDTO.getPhone(),
                userDTO.getRole(),
                imageRepository.findByLink(userDTO.getImage())
        );
    }

    public User mapFromRegister(Register registerDTO) {
        logger.info("ДТО регистрации - " + registerDTO);
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setPhone(registerDTO.getPhone());
        user.setRole(registerDTO.getRole());
        return user;
    }

}
