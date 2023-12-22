package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface UserMapper {
    @Named("idToUrl")
    static String idToUrl(int id) {
        return "/image/" + id;
    }

    @Mapping(source = "pk", target = "id")
    @Mapping(source = "imageEntity.id", target = "image", qualifiedByName = "idToUrl")
    User userToUserDTO(UserEntity userEntity);
}