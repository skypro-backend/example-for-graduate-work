package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;

import java.time.ZoneId;

/**
 * Преобразует сущность комментария в DTO и обратно.
 */
@Component
public class CommentMapper {

    /**
     * Создаёт сущность комментария.
     *
     * @param request данные комментария
     * @param author  автор комментария
     * @param ad      объявление
     * @return новая сущность комментария
     */
    public CommentEntity toEntity(
            CreateOrUpdateComment request,
            UserEntity author,
            AdEntity ad
    ) {
        CommentEntity entity = new CommentEntity();

        entity.setText(request.getText());
        entity.setAuthor(author);
        entity.setAd(ad);

        return entity;
    }

    /**
     * Преобразует сущность комментария в DTO.
     *
     * @param entity сущность комментария
     * @return информация о комментарии
     */
    public Comment toDto(CommentEntity entity) {
        UserEntity author = entity.getAuthor();

        return new Comment(
                author.getId().intValue(),
                buildAuthorImageUrl(author),
                author.getFirstName(),
                entity.getCreatedAt()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .toEpochMilli(),
                entity.getId().intValue(),
                entity.getText()
        );
    }

    /**
     * Обновляет текст комментария.
     *
     * @param entity  изменяемый комментарий
     * @param request новый текст
     */
    public void updateEntity(
            CommentEntity entity,
            CreateOrUpdateComment request
    ) {
        entity.setText(request.getText());
    }

    private String buildAuthorImageUrl(UserEntity author) {
        if (author.getImage() == null || author.getId() == null) {
            return null;
        }

        return "/users/" + author.getId() + "/image";
    }
}
