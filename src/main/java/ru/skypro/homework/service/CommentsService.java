package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    /** Создание комментария, по id
     @param userLogin     {@link String}
     @param id            {@link Long}
     @param createComment {@link CreateOrUpdateComment}
     @return объект {@link Optional<Comments>} */
    Optional<Comments> createComment(String userLogin, Long id, CreateOrUpdateComment createComment);

    /** Получение всех комментариев, по id, в List</>
     @param id {@link Integer}
     @return объект {@link List<Comments>} */
    Comments listCommentsAdById(int id);

    /** Редактирование комментария по его id
     @param userLogin     {@link String}
     @param adId          {@link Long}
     @param commentId     {@link Long}
     @param updateComment {@link CreateOrUpdateComment}
     @return объект {@link Optional<Comments>} */
    Optional<Comments> editComment(String userLogin, Long adId, Long commentId, CreateOrUpdateComment updateComment);

    /** Удаление комментария по его id
     @param userLogin {@link String}
     @param adId      {@link Long}
     @param commentId {@link Long}
     @return true, если комментарий удален, false, если не удален */
    boolean deleteById(String userLogin, Long adId, Long commentId);

}