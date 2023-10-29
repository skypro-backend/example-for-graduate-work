package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.model_dto.CommentDto;
import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.Comment;

import java.util.List;

/**
 * Сервис для работы с комментариями.
 */

public interface CommentService {

      List <CommentDto> getAdComments (Integer id); // Получение комментариев объявления

      // Добавление комментария к объявлению
      Comment addCommentToAd (Integer id , CreateOrUpdateCommentDto createOrUpdateCommentDto , Authentication authentication);

      void deleteComment (Integer adId , Integer commentId , Authentication authentication); // Удаление комментария

      // Обновление комментария
      Comment updateComment (Integer adId , Integer commentId , CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
