package ru.skypro.homework.service.auth.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.LoginDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.comments.Comment;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.exceptions.NotFoundException;
import ru.skypro.homework.mappers.CustomUserMapper;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.ads.AdsRepository;
import ru.skypro.homework.service.auth.AuthService;
import ru.skypro.homework.service.users.impl.UserCustomService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserCustomService userCustomService;
    private final UserMapper userMapper;
    private final CustomUserMapper customUserMapper;
    private final PasswordEncoder encoder;
    private final AdsRepository adsRepository;

    public AuthServiceImpl(UserCustomService userCustomService,
                           UserMapper userMapper,
                           CustomUserMapper customUserMapper,
                           PasswordEncoder encoder,
                           AdsRepository adsRepository) {
        this.userCustomService = userCustomService;
        this.userMapper = userMapper;
        this.customUserMapper = customUserMapper;
        this.encoder = encoder;
        this.adsRepository = adsRepository;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto, customUserMapper);
        UserCustom userCustom = new UserCustom(user);
        if (!userCustomService.userExists(userCustom.getUsername())) {
            userCustomService.createUser(userCustom);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        if (userCustomService.userExists(username)) {
            String currentPassword = loginDto.getPassword();
            UserDetails userDetails = userCustomService.loadUserByUsername(username);
            return encoder.matches(currentPassword, userDetails.getPassword());
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserAllowedToChangeAds(Authentication authentication, Integer adId) {
        if (authentication.getPrincipal() instanceof UserCustom) {
            Integer userid = ((UserCustom) authentication.getPrincipal()).getId();
            Optional<Ad> adOptional = adsRepository.findById(adId);
            if (adOptional.isEmpty()) {
                throw new NotFoundException("Объявление с таким id не найдено: " + adId);
            } else {
                Ad ad = adOptional.get();
                Integer authorId = ad.getAuthor().getId();
                return Objects.equals(userid, authorId);
            }
        }
        return false;
    }

    @Override
    public boolean isUserAllowedToChangeComments(Authentication authentication, Integer adId, Integer commentId) {
        if (authentication.getPrincipal() instanceof UserCustom) {
            Integer userid = ((UserCustom) authentication.getPrincipal()).getId();
            Optional<Ad> adOptional = adsRepository.findById(adId);
            if (adOptional.isEmpty()) {
                throw new NotFoundException("Объявление с таким id не найдено: " + adId);
            } else {
                Ad ad = adOptional.get();
                List<Comment> comments = ad.getComments();
                List<Comment> collect = comments.stream().filter(comment -> Objects.equals(comment.getPk(), commentId)).collect(Collectors.toList());
                if (collect.isEmpty()) {
                    throw new NotFoundException("Комментарий с таким id не найден: " + commentId);
                } else {
                    Comment comment = collect.get(0);
                    User author = comment.getAuthor();
                    Integer authorId = author.getId();
                    return Objects.equals(authorId, userid);
                }
            }
        }
        return false;
    }

}
