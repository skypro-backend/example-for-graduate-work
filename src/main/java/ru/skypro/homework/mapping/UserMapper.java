package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Converts {@link User} instance to {@link UserDto} instance
     *
     * @param user {@link User} instance, {}nullable
     * @return {@link UserDto} instance
     */
    @Mapping(source = "name", target = "firstName")
    @Mapping(source = "surname", target = "lastName")
    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "idImage", target = "image")
    UserDto userToDto(User user);
    /*
    User instance fields	UserDto instance field	Note

private Long id;	private Integer id;	Mapped by default

private String name;	private String firstName;	Apply annotation

private String surname;	private String lastName;	Apply annotation

private String phoneNumber;	private String phone;	Apply annotation

private String email;	private String email;	Mapped by default

private Role userRole;	private Role userRole;	Mapped by default

private String idImage; private String image;	Apply annotation
     */
}
