package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;

@Slf4j
@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private ModelMapper mapper;

    public String uploadAvatar(String username, MultipartFile file) {
        return "done";
    }

    private UserDto convertToUserDto(UserEntity userEntity) {
        return mapper.map(userEntity, UserDto.class);
    }

    private UserEntity convertToUserEntity(UserDto userDto) {
        return mapper.map(userDto, UserEntity.class);
    }
}
