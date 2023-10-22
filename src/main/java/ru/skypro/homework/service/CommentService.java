package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.adsDTO.*;
import javax.transaction.Transactional;

@Service
public abstract class CommentService {
    //Получает список комментариев к объявлению по его идентификатору
    public abstract CommentsDTO getComments(Integer id);

    //Добавляет новый комментарий к объявлению.
    public abstract CommentDTO addComment(Integer id, CreateCommentDTO createComment, String email);

    //Удаляет комментарий по идентификаторам объявления и комментария.
    @Transactional
    public abstract void deleteComment(Integer adId, Integer id);

    //Обновляет текст комментария по идентификаторам объявления и комментария.
    public abstract CommentDTO updateComment(Integer adId, Integer id, CreateCommentDTO createComment);

    //Получает объект CommentDto по идентификаторам объявления и комментария.
    public abstract CommentDTO getCommentDto(Integer adId, Integer id);

}
