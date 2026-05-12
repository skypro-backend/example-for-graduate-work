package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.controller.CommentController;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.entity.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для управления комментариями к объявлениям.
 * <p>
 * Обеспечивает бизнес-логику:
 * <ul>
 *   <li>Получение списка комментариев по объявлению</li>
 *   <li>Добавление нового комментария</li>
 *   <li>Редактирование и удаление существующих комментариев</li>
 *   <li>Проверка прав доступа (автор или администратор)</li>
 * </ul>
 * Все операции выполняются через репозитории ({@link CommentRepository}, {@link AdRepository}, {@link UserRepository}),
 * преобразование данных — с помощью {@link CommentMapper}.
 *
 * @see CommentService — основной интерфейс, используемый контроллерами
 * @see CommentController — использует этот сервис для обработки HTTP-запросов
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    /**
     * Репозиторий для доступа к данным комментариев.
     */
    private final CommentRepository commentRepository;

    /**
     * Репозиторий для доступа к данным объявлений (проверка существования объявления).
     */
    private final AdRepository adRepository;

    /**
     * Репозиторий для доступа к данным пользователей (проверка авторства и прав).
     */
    private final UserRepository userRepository;

    /**
     * Маппер для преобразования сущности {@link CommentEntity} в DTO ({@link Comment}).
     */
    private final CommentMapper commentMapper;

    /**
     * Получение всех комментариев к объявлению по его идентификатору.
     * <p>
     * Возвращает список комментариев с информацией: текст, автор, дата создания, ссылка на аватар.
     *
     * @param adId идентификатор объявления
     * @return объект {@link Comments}, содержащий общее количество и список комментариев
     * @throws EntityNotFoundException если объявление не найдено
     */
    @Override
    public Comments getComments(Integer adId) {
        AdEntity ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));

        List<Comment> comments = commentRepository.findByAdPk(adId).stream().map(commentMapper::toDto).collect(Collectors.toList());

        Comments result = new Comments();
        result.setCount(comments.size());
        result.setResults(comments);
        return result;
    }

    /**
     * Добавление нового комментария к объявлению.
     * <p>
     * Устанавливает автором пользователя, определённого по email. Фиксирует текущее время как дату создания.
     *
     * @param adId     идентификатор объявления
     * @param dto      DTO с текстом комментария
     * @param username email текущего пользователя (автора)
     * @return созданный объект {@link Comment}
     * @throws EntityNotFoundException если объявление или пользователь не найдены
     */
    @Override
    @Transactional
    public Comment addComment(Integer adId, CreateOrUpdateComment dto, String username) {
        AdEntity ad = adRepository.findById(adId).orElseThrow(() -> new EntityNotFoundException("Объявление с ID " + adId + " не найдено"));
        UserEntity author = userRepository.findByEmail(username).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        CommentEntity entity = new CommentEntity();
        entity.setText(dto.getText());
        entity.setAd(ad);
        entity.setAuthor(author);
        entity.setCreatedAt(java.time.LocalDateTime.now());

        CommentEntity saved = commentRepository.save(entity);
        return commentMapper.toDto(saved);
    }

    /**
     * Удаление комментария по его идентификатору.
     * <p>
     * Доступно только автору комментария или администратору.
     *
     * @param adId      идентификатор объявления (для проверки контекста)
     * @param commentId идентификатор комментария
     * @param email     email текущего пользователя
     * @throws EntityNotFoundException если комментарий или пользователь не найдены
     * @throws AccessDeniedException   если у пользователя нет прав на удаление
     */
    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, String email) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Комментарий с ID " + commentId + " не найден"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!comment.getAuthor().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("Нет прав на удаление");
        }

        commentRepository.deleteById(commentId);
    }

    /**
     * Частичное обновление текста комментария.
     * <p>
     * Доступно только автору или администратору. Обновляет только поле {@code text}.
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @param dto       DTO с новым текстом
     * @param email     email текущего пользователя
     * @return обновлённый объект {@link Comment}
     * @throws EntityNotFoundException если комментарий или пользователь не найдены
     * @throws AccessDeniedException   если нет прав на редактирование
     */
    @Override
    @Transactional
    public Comment updateComment(Integer adId, Integer commentId, CreateOrUpdateComment dto, String email) {
        CommentEntity existing = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("Комментарий с ID " + commentId + " не найден"));

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));

        if (!existing.getAuthor().getId().equals(user.getId()) && user.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("У вас нет прав на редактирование этого комментария");
        }

        existing.setText(dto.getText());
        CommentEntity updated = commentRepository.save(existing);
        return commentMapper.toDto(updated);
    }
}