package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;

import java.time.ZoneId;

/**
 * Маппер для преобразования сущности {@link CommentEntity} в DTO {@link Comment} и обратно.
 * <p>
 * Использует MapStruct для автоматического отображения полей. Особое внимание уделяется:
 * <ul>
 *   <li>Преобразованию времени создания комментария в миллисекунды (Unix timestamp)</li>
 *   <li>Формированию ссылки на аватар автора комментария</li>
 *   <li>Игнорированию полей, управляемых сервером (ID, дата создания, связи)</li>
 * </ul>
 *
 * @see Comment — DTO для передачи данных комментария фронтенду
 * @see CommentEntity — JPA-сущность комментария с полной информацией
 */
@Mapper(componentModel = "spring", imports = {ZoneId.class})
public interface CommentMapper {

    /**
     * Преобразует сущность комментария в DTO для передачи клиенту.
     * <p>
     * Особенности:
     * <ul>
     *   <li>{@code createdAt} конвертируется в миллисекунды с 1970 года (UTC)</li>
     *   <li>{@code authorImage} формируется как "/users/{id}/image" — используется {@link ru.skypro.homework.controller.UserController}</li>
     *   <li>{@code author} — ID пользователя, а не объект</li>
     * </ul>
     *
     * @param commentEntity сущность комментария из базы данных
     * @return объект {@link Comment} с данными для отображения на фронтенде
     */
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(expression = "java(\"/users/\" + commentEntity.getAuthor().getId() + \"/image\")", target = "authorImage")
    @Mapping(expression = "java(commentEntity.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())", target = "createdAt")
    Comment toDto(CommentEntity commentEntity);

    /**
     * Преобразует DTO комментария в сущность для сохранения в базе.
     * <p>
     * Поля, устанавливаемые сервером, игнорируются:
     * <ul>
     *   <li>{@code pk} — генерируется автоматически</li>
     *   <li>{@code createdAt} — устанавливается текущее время</li>
     *   <li>{@code ad} — заполняется сервисом при добавлении к объявлению</li>
     *   <li>{@code author} — устанавливается из контекста аутентификации</li>
     * </ul>
     *
     * @param comment DTO с текстом комментария от пользователя
     * @return частично заполненная сущность {@link CommentEntity}
     */
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "author", ignore = true)
    CommentEntity toEntity(Comment comment);
}