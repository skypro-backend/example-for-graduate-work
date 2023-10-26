package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper
public abstract class CommentMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Mapping(target = "author", expression = "java(getUserFromAuthentication(authentication).getId())")
    //@Mapping(target = "authorImage", expression = "java(getImagePathFromUser(getUserFromAuthentication(authentication)))")
    @Mapping(target = "authorImage", expression = "java(null)")
    @Mapping(target = "authorFirstName", expression = "java(getUserFromAuthentication(authentication).getFirstName())")
    @Mapping(target = "createdAt", expression = "java(getLongFromLocalDateTime(comment.getCreatedAt()))")
    @Mapping(target = "pk", expression = "java(comment.getId())")
    public abstract CommentDto entityToCommentDto(Comment comment,Authentication authentication);

    //todo вот тут при обновлении дата создания будет также меняться, можно ли оставить так?
    @Mapping(target = "createdAt", expression = "java(getNowLocalDateTime())")
    @Mapping(target = "user", expression = "java(getUserFromAuthentication(authentication))")
    @Mapping(target = "ad", expression = "java(getAdByAdId(adId))")
    public abstract Comment createOrUpdateCommentDtoToEntity(Integer adId, CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication);


    protected Long getLongFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    protected LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }

    protected String getImagePathFromUser(User user) {
        if (user.getUserImage().getFilePath() == null) {
            throw new ImageNotFoundException();
        }
        return user.getUserImage().getFilePath();
    }

    protected User getUserFromAuthentication(Authentication authentication) {
        return userRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }
    protected Ad getAdByAdId(Integer adId) {
        return adRepository.findById(adId)
                .orElseThrow(() ->
          //          log.info("Директория успешно создана: "
                new AdNotFoundException(adId));
    }


}
