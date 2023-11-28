package ru.skypro.homework.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.ImageRepository;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ImageRepository imageRepository;

    public User mapToDTO(ru.skypro.homework.model.User user) {
        return new User(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getRole(),
                user.getImage().getLink()
        );
    }

    public ru.skypro.homework.model.User mapToEntity(User userDTO) {
        return new ru.skypro.homework.model.User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhone(),
                userDTO.getRole(),
                imageRepository.findByLink(userDTO.getImage())
        );
    }

}
