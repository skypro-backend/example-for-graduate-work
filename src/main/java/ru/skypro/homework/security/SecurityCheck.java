package ru.skypro.homework.security;
import lombok.extern.java.Log;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import lombok.RequiredArgsConstructor;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;


import java.util.Optional;

@Log
@Component
@RequiredArgsConstructor
public class SecurityCheck {
      private final AdRepository adRepository;
      private final CommentRepository commentRepository;
      private final UserRepository userRepository;

      /**
       * Проверяет, имеет ли user права администратора
       */
      public boolean isAdmin(User user) {
            log.info("completed isAdmin");
            return user.getRole().equals(Role.ADMIN);
      }

      /**
       * Проверяет соответствие объявления и автора
       */
      public boolean isAuthorAd (User user, Ad ad) {
            log.info("completed isAuthorAd");
            return ad.getAuthor().equals(user);
      }

      /**
       * Проверяет соответствие комментария и автора
       */
      public boolean isAuthorComment (User user, Comment comment) {
            log.info("completed isAuthorComment");
            return comment.getAuthor().equals(user);
      }

      /**
       * Проверяет наличие объявления по идентификатору
       */
      public Ad checkAd(Integer idAd) {
            Optional <Ad> ad = adRepository.findById(idAd);
            if (ad.isEmpty()) {
                  log.info ("Объявление не найдено");
                  throw new NullPointerException();
            } else {
                  return ad.get ();
            }
      }

      /**
       * Проверяет наличие комментария по идентификатору
       */
      public Comment checkComment(Integer id) {
            Optional<Comment> comment = commentRepository.findById(id);
            if (comment.isEmpty()) {
                  log.info("Комментарий не найден");
                  throw new NullPointerException();
            } else {
                  return comment.get();
            }
      }

      /**
       * Проверяет наличие авторизованного пользователя
       */
      public User checkUser(Authentication authentication) {
            Optional<User> user = userRepository.findByEmail(authentication.getName());
            if (user.isEmpty()) {
                  log.info("Пользователь не найден");
                  throw new NullPointerException();
            } else {
                  return user.get();
            }
      }
}
