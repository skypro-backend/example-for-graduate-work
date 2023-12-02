package ru.skypro.homework.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

    public ru.skypro.homework.model.User mapFromRegister(Register registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setRole(registerDTO.getRole());
        return user;
    }

}
