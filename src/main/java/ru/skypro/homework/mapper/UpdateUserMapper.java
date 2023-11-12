package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UpdateUserMapper {
//    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);
    @Mapping(target = "firstName", source = "updateUserDto.firstName")
    @Mapping(target = "lastName", source = "updateUserDto.lastName")
    @Mapping(target = "phone", source = "updateUserDto.phone")
    User toModel(UpdateUserDto updateUserDto);
}
