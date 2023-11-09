package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewPasswordMapper {
    NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);
    @Mapping(source = "user.password", target = "currentPassword")
//    @Mapping(source = "user.newPassword", target = "newPassword")
    NewPasswordDto toDto(User user);

//    @Mapping(source = "currentPassword", target = "user.currentPassword")
    @Mapping(source = "newPassword", target = "user.password")
    User toModel(RegisterDto registerDto);
}
