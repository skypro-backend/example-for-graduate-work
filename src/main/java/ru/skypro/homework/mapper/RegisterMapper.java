package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterMapper {
    @Mapping(source = "userName", target = "user.userName")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "phone", target = "user.phone")
    @Mapping(source = "role", target = "user.role")
    User toModel(RegisterDto registerDto);
}
