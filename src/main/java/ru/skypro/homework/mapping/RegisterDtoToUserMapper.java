package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;

/**
 * <h2>RegisterDtoToUserMapper</h2>
 * <p>
 * Mapper to create User instance from RegisterDto.
 * Intended to be used in createUser method of {@link ru.skypro.homework.service.impl.AuthServiceImpl}
 */
@Mapper
public interface RegisterDtoToUserMapper {
    RegisterDtoToUserMapper INSTANCE = Mappers.getMapper(RegisterDtoToUserMapper.class);

    public User registerDtoToUserMapper(RegisterDto registerDto);

}
