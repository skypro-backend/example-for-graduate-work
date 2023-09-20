package ru.skypro.homework.service.users.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.exceptions.BadRequestException;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.users.UsersRepository;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
public class UserServiceImpl implements UserService {

    private final UserCustomService userCustomService;
    private final UserMapper userMapper;
    private final ImageService imageService;
    private final UsersRepository usersRepository;
    private final HttpServletRequest httpServletRequest;

    public UserServiceImpl(UserCustomService userCustomService,
                           UserMapper userMapper,
                           ImageService imageService,
                           UsersRepository usersRepository, HttpServletRequest httpServletRequest) {
        this.userCustomService = userCustomService;
        this.userMapper = userMapper;
        this.imageService = imageService;
        this.usersRepository = usersRepository;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public void setPassword(NewPasswordDto newPasswordDto) {
        userCustomService.changePassword(newPasswordDto.getCurrentPassword(), newPasswordDto.getNewPassword());
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public UserDto getUser() {
        User author = getAuthor();
        return userMapper.toUserDto(author);
    }

    @Override
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

    @Override
    @Transactional
    public void updateUserImage(MultipartFile image) throws IOException {
        User author = getAuthor();
        String urlToImage = author.getImage();
        if (urlToImage != null) {
            author.setImage(null);
            usersRepository.save(author);
            imageService.deleteImageOfAvatars(urlToImage);
        }
        String urlToImageOfAvatar = imageService.consumeImageOfAvatar(image);
        author.setImage(urlToImageOfAvatar);
        usersRepository.save(author);
    }

    @Override
    public byte[] getUserImage() throws IOException {
        User author = getAuthor();
        String urlToImage = author.getImage();
        if (urlToImage == null || urlToImage.isEmpty()) {
            throw new NotFoundException("У пользователя нет аватарки");
        }
        try {
            Path fullPathToImageOfAvatars = imageService.getFullPathToImageOfAvatars(urlToImage);
            return imageService.imageToByteArray(fullPathToImageOfAvatars);
        } catch (NoSuchFileException exception) {
            throw new NotFoundException("Файл с картинкой не найден по данному адресу" + urlToImage);
        } catch (IOException exception) {
            throw new IOException("Ошибка при чтении файла ", exception);
        }
    }

    @Override
    public User getAuthor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCustom userCustom = (UserCustom) authentication.getPrincipal();
        return userCustom.getUser();
    }

}