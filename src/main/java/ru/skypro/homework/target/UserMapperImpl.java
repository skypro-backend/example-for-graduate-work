package ru.skypro.homework.target;


import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2024-01-13T16:42:47+0300",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setAvatar( avatarToString( entity.getAvatar() ) );
        userDto.setId( entity.getId() );
        userDto.setEmail( entity.getEmail() );
        userDto.setFirstName( entity.getFirstName() );
        userDto.setLastName( entity.getLastName() );
        userDto.setPhone( entity.getPhone() );
        userDto.setRole( entity.getRole() );

        return userDto;
    }

    @Override
    public User userDtoToUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setEmail( dto.getEmail() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setPhone( dto.getPhone() );

        return user;
    }

    @Override
    public User registerToUser(Register dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        if ( dto.getRole() != null ) {
            user.setRole( dto.getRole() );
        }
        else {
            user.setRole( Role.USER );
        }
        user.setEmail( dto.getUsername() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setPassword( dto.getPassword() );
        user.setPhone( dto.getPhone() );

        return user;
    }
}
