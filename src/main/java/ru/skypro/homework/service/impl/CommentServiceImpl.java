package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.model_dto.CommentDto;

import ru.skypro.homework.dto.model_dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.mapper.CommentMapper;

import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.SecurityCheck;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Реализация интерфейса CommentService для управления комментариями.
 */
@Log
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

      private final UserRepository userRepository;
      private final CommentRepository commentRepository;
      private final CommentMapper commentMapper;
      private final AdRepository adRepository;
      private final SecurityCheck securityCheck;

      @Override
      public List <CommentDto> getAdComments (Integer id) {
            log.info("Метод получения комментариев");
            return commentMapper.toCommentsDto (commentRepository.findAllByAdId(id));
      }

      @Override
      public Comment addCommentToAd (Integer id , CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                     Authentication authentication) {
            User user = userRepository.findByEmail (SecurityContextHolder.getContext ()
                      .getAuthentication ().getName ()).orElseThrow ();
            Ad ad = adRepository.findById(id).orElseThrow();
            log.info("Был вызван метод для добавления комментария к объявлениюу");
            Comment comment = commentMapper.toCommentyCr (createOrUpdateCommentDto);
            comment.setAuthor (user);
            comment.setAd(ad);
            comment.setCreatedAt(Instant.now());
            return commentRepository.save(comment);
      }

      @Override
      public void deleteComment (Integer adId , Integer commentId , Authentication authentication) {
            log.info("Был вызван метод для удаления комментария по идентификатору");
            User user = userRepository.findByEmail (SecurityContextHolder.getContext ()
                      .getAuthentication ().getName ()).orElseThrow ();
            Comment comment = commentRepository.findAllByAdId (adId).get (commentId);
            securityCheck.checkComment (adId);
            if (securityCheck.isAdmin (user) || securityCheck.isAuthorComment (user, comment)) {
                  commentRepository.delete(comment);
            }
      }

      @Override
      public Comment updateComment (Integer adId, Integer commentId,
                                    CreateOrUpdateCommentDto createOrUpdateCommentDto) {
            log.info("Был вызван метод для обновления комментария");
            User user = userRepository.findByEmail (SecurityContextHolder.getContext ()
                      .getAuthentication ().getName ()).orElseThrow ();
            Ad ad = adRepository.findById(adId).orElseThrow();
            Comment comment = commentRepository.findAllByAdId (adId).get (commentId);
            securityCheck.checkComment (adId);

            if (securityCheck.isAdmin (user) || securityCheck.isAuthorComment (user, comment)) {
                  comment.setText (createOrUpdateCommentDto.getText ());
            }
            return commentRepository.save(comment);
      }


}
