package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.PasswordDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.utils.MyMapper;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final MyMapper mapper;

    public void updatePassword(PasswordDto passwordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // since username is email it should be unique
        String username = authentication.getName();
        UserEntity userEntity = userRepository.findByUsername(username);
        Long id = userEntity.getId();
        // here working with AuthRepository(?) should follow
        // saving new password by user id
        // TODO implement the above mentioned functionality
    }

    public String uploadAvatar(String username, MultipartFile file) {
        return "done";
    }
}
