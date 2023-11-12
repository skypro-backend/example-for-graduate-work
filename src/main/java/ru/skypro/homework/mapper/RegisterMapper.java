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
    @Mapping(source = "registerDto.username", target = "userName")
    @Mapping(source = "registerDto.password", target = "password")
    @Mapping(source = "registerDto.firstName", target = "firstName")
    @Mapping(source = "registerDto.lastName", target = "lastName")
    @Mapping(source = "registerDto.phone", target = "phone")
    @Mapping(source = "registerDto.role", target = "role")
    User toModel(RegisterDto registerDto);
}
