package ru.skypro.homework.service.users.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.exceptions.BadRequestException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.image.ImageService;

//import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class UserService {

    private final UserCustomService userCustomService;
    private final UserMapper userMapper;
    private final ImageService imageService;

    public UserService(UserCustomService userCustomService,
                       UserMapper userMapper,
                       ImageService imageService
                       ) {
        this.userCustomService = userCustomService;
        this.userMapper = userMapper;
        this.imageService = imageService;
    }

    public void setPassword(NewPasswordDto newPasswordDto) {
        userCustomService.changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
//        HttpSession session = httpServletRequest.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
    }

    public UserDto getUser() {
        User author = getAuthor();
        return userMapper.toUserDto(author);
    }

    public UpdateUserDto updateUser(UpdateUserDto updateUserDto) {
        if (updateUserDto != null) {
            User author = getAuthor();
            userMapper.updateUser(updateUserDto, author);
            UserCustom userCustom = new UserCustom(author);
            userCustomService.updateUser(userCustom);
            return userMapper.toUpdateUserDto(author);
        } else {
            throw new BadRequestException("UpdateUserDto пустой");
        }
    }

    public void updateUserImage(MultipartFile image) throws IOException {
        User author = getAuthor();
        String urlToImage = author.getImage();
        if (urlToImage != null) {
            imageService.deleteImageOfAvatars(urlToImage);
        }
        String urlToImageOfAvatar = imageService.consumeImageOfAvatar(image);
        author.setImage(urlToImageOfAvatar);
        UserCustom userCustom = new UserCustom(author);
        userCustomService.updateUser(userCustom);
    }

    public byte[] getUserImage() throws IOException {
        User author = getAuthor();
        String urlToImage = author.getImage();
        Path fullPathToImageOfAvatars = imageService.getFullPathToImageOfAvatars(urlToImage);
        return imageService.imageToByteArray(fullPathToImageOfAvatars);
    }

    public static User getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCustom userCustom = (UserCustom) authentication.getPrincipal();
        return userCustom.getUser();
    }


}