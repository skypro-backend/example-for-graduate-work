package ru.skypro.homework.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.entity.Comment;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.exception.custom_exception.AdNotFoundException;
import ru.skypro.homework.exception.custom_exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.projection.CommentView;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.user.UserService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserService userService;


    /**
     * Возвращает список комментариев объявления
     *
     * @param id идентификатор объявления
     * @return количество комметариев, оставленных к объявлению с указанным id, и их список
     */
    @Override
    public Comments getAllCommentsByAdId(Integer id) {
        List<Comment> list = commentRepository.findAllByAd_Pk(id);
        return new Comments()
                .setResults(list.stream()
                        .map(CommentMapper::toView)
                        .collect(Collectors.toList()))
                .setCount(list.size());
    }

    /**
     * Сохраняет в БД новый комментарий к объявлению с указанным id и возвращает описание этого комментария
     *
     * @param id идентификатор объявления
     * @param comment комментарий
     * @param authentication данные авторизированного пользователя
     * @return описание добавленного комментария
     * @throws AdNotFoundException если объявление с указанным id не найдено
     */
    @Override
    public CommentView createComment(Integer id, CreateOrUpdateComment comment,Authentication authentication) {
        return CommentMapper.toView(commentRepository.save(new Comment()
                .setCreatedAt(Instant.now())
                .setAd(adRepository.findById(id).orElseThrow(AdNotFoundException::new))
                .setText(comment.getText())
                .setUser(UserMapper.fromDTO(userService.getCurrentUser(authentication)))));
    }

    /**
     * Удаляет комментарий из БД
     *
     * @param commentId идентификатор комментария
     * @param adId идентификатор объявления
     */
    @Override
    public void deleteComment(Integer commentId,Integer adId) {
        commentRepository.deleteCommentByPkAndAd_Pk(commentId, adId);
    }

    /**
     * Обновляет в БД комментарий к объявлению и возвращает его описание
     *
     * @param commentId идентификатор комментария
     * @param adId идентификатор объявления
     * @param comment комментарий
     * @return описание комментария после обновления
     * @throws CommentNotFoundException если комментарий с указанным commentId не найден
     */
    @Override
    public CommentView updateComment(Integer commentId,Integer adId, CreateOrUpdateComment comment) {
        Comment commentResult = commentRepository.findByPkAndAd_Pk(commentId, adId).orElseThrow(CommentNotFoundException::new);
        commentResult.setText(comment.getText())
                .setCreatedAt(Instant.now());
        return CommentMapper.toView(commentRepository.save(commentResult));
    }

    /**
     * Возвращает DTO комментария, найденного по id
     *
     * @param id идентификатор комментария
     * @return DTO комментария
     * @throws CommentNotFoundException если комментарий с указанным id не найден
     */
    @Override
    public CommentDTO findById(Integer id) {
        return commentRepository.findById(id).map(CommentMapper::fromComment).orElseThrow(CommentNotFoundException::new);
    }
}