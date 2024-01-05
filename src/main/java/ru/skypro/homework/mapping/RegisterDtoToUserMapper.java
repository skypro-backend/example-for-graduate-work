package ru.skypro.homework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    /*
    @Getter
@Data
public class RegisterDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

}
     */
    @Mapping(source = "username", target = "email")
    // password field is filled automatically
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surname")
    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "role", target = "userRole")
    public User registerDtoToUserMapper(RegisterDto registerDto);
/*
public class User {

    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Role userRole;
    private String idImage;
    private String password;
 */
}
