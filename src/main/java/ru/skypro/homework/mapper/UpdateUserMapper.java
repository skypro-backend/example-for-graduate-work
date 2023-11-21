package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UpdateUserMapper {
    UpdateUserMapper INSTANCE = Mappers.getMapper(UpdateUserMapper.class);


    User toModel(UpdateUserDto updateUserDto, @MappingTarget User user);

    UpdateUserDto toDTO(User user);
}
