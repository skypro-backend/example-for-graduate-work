package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentEntity;

import java.util.Optional;

/**
 * Маппер для преобразования сущности {@link CommentEntity} в DTO и обратно.
 * Основные функции:
 * - преобразование сущности комментария в DTO (Comment);
 * - преобразование DTO (CreateOrUpdateComment) в сущность для создания/обновления комментария.
 * Также формирует ссылку на аватар автора комментария.
 */
@Component
public class CommentMapper {

    /**
     * Преобразует сущность комментария в DTO.
     * Формирует ссылку на аватар автора в формате /images/{id}.
     * @param entity сущность комментария
     * @return Optional с DTO или пустой Optional
     */
    public Optional<Comment> toDto(CommentEntity entity) {
        return Optional.ofNullable(entity)
                .map(e -> {
                    Comment dto = new Comment();
                    dto.setPk(e.getId());
                    dto.setText(e.getText());
                    dto.setCreatedAt(e.getCreatedAt());
                    if (e.getAuthor() != null) {
                        dto.setAuthor(e.getAuthor().getId());
                        dto.setAuthorFirstName(e.getAuthor().getFirstName());
                        if (e.getAuthor().getAvatar() != null) {
                            dto.setAuthorImage("/images/" + e.getAuthor().getAvatar().getId());
                        } else {
                            dto.setAuthorImage(null);
                        }
                    }
                    return dto;
                });
    }

    /**
     * Преобразует DTO в сущность комментария.
     * Автоматически устанавливает текущее время создания.
     * @param dto DTO с текстом комментария
     * @return Optional с сущностью или пустой Optional
     */
    public Optional<CommentEntity> toEntity(CreateOrUpdateComment dto) {
        return Optional.ofNullable(dto)
                .map(d -> {
                    CommentEntity entity = new CommentEntity();
                    entity.setText(d.getText());
                    entity.setCreatedAt(System.currentTimeMillis());
                    return entity;
                });
    }
}
