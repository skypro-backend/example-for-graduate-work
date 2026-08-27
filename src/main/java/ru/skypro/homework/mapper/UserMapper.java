package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;

import java.util.Optional;

@Component
public class UserMapper {

    public Optional<User> toDto(UserEntity entity) {
            return Optional.ofNullable(entity)
                    .map(e -> {
                        User dto = new User();
                        dto.setId(e.getId());
                        dto.setEmail(e.getEmail());
                        dto.setFirstName(e.getFirstName());
                        dto.setLastName(e.getLastName());
                        dto.setPhone(e.getPhone());
                        dto.setRole(e.getRole());
                        if (e.getAvatar() != null) {
                            dto.setImage("/images/" + e.getAvatar().getId());
                        } else {
                            dto.setImage(null);
                        }
                        return dto;
                    });
    }


    public Optional<UserEntity> toEntity(User dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    UserEntity entity = new UserEntity();
                    entity.setId(d.getId());
                    entity.setEmail(d.getEmail());
                    entity.setFirstName(d.getFirstName());
                    entity.setLastName(d.getLastName());
                    entity.setPhone(d.getPhone());
                    entity.setRole(d.getRole());
                    entity.setImage(d.getImage());
                    return entity;
                });
    }
}
