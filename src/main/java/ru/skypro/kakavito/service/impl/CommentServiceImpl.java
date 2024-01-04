package ru.skypro.kakavito.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.kakavito.dto.CommentDTO;
import ru.skypro.kakavito.dto.CommentsDTO;
import ru.skypro.kakavito.dto.CreateOrUpdateCommentDTO;
import ru.skypro.kakavito.dto.Role;
import ru.skypro.kakavito.exceptions.AdNotFoundException;
import ru.skypro.kakavito.exceptions.CommentNotFoundException;
import ru.skypro.kakavito.exceptions.UserNotAuthorizedException;
import ru.skypro.kakavito.exceptions.UserNotFoundException;
import ru.skypro.kakavito.mappers.CommentMapper;
import ru.skypro.kakavito.model.Comment;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.repository.AdRepo;
import ru.skypro.kakavito.repository.CommentRepo;
import ru.skypro.kakavito.repository.UserRepo;
import ru.skypro.kakavito.service.CommentService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    private final AdRepo adRepo;
    private final UserRepo userRepo;
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    /**
     * Возвращет все комментарии для конкретного объявления
     *
     * @param id Id объявления
     * @return CommentsDTO
     * @see CommentsDTO
     */
    @Override
    public CommentsDTO getAllByCommentById(int id) {
        logger.info("Comment getAllByCommentById is running");
//        commentRepo.count();
        return commentMapper.toCommentsDTO(commentRepo.getAllByAdId(id));
    }

    /**
     * Добавление нового комментария к объявлению
     *
     * @param adId Id объявления
     * @param createOrUpdateCommentDTO DTO объект, представляющий новый комментарий
     * @return CommentDTO нового комментария
     * @throws AdNotFoundException, если объявления с переданным Id не существует
     */
    @Override
    public CommentDTO createComment(int adId, CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        logger.info("Comment createComment is running");
        Comment newComment = new Comment();
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setText(createOrUpdateCommentDTO.getText());
        newComment.setAd(adRepo.findById(adId)
                .orElseThrow(() -> new AdNotFoundException("Объявление с Id: " + adId + " не найдено")));
        newComment.setUser(getCurrentUser());
        return commentMapper.toDto(commentRepo.save(newComment));
    }

//            CreateOrUpdateCommentDTO text) {
//        logger.info("Comment createComment is running");
//        final var comment = commentRepo.getCommentByText(String.valueOf(text)).orElseThrow();
//        comment.setText(String.valueOf(text));
//        return commentRepo.save(comment);
//    }

//    @Override
//    public CommentDTO findById(int commentId) {
//        logger.info("Comment findById is running");
//        return commentRepo.findById(Math.toIntExact(commentId)).orElseThrow(() -> new CommentNotFoundException("Comment not found"));
//    }

    /**
     * Удаление ранее опубликованного комментария, принадлежащего текущему пользователю
     *
     * @param adId Id объявления
     * @param commentId Id комментария
     * @throws CommentNotFoundException   если комментария с переданным Id не существует
     * @throws UserNotAuthorizedException если пользователь не является автором комментария и не админ
     */
    @Override
    public void deleteComment(int adId, int commentId) {
        logger.info("Comment deleteComment is running");
        if (isCurrentUserAuthorized(commentId)) {
            commentRepo.deleteById(Math.toIntExact(commentId));
        } else {
            throw new UserNotAuthorizedException("У пользователя c id: " + getCurrentUser().getId() +
                    " недостаточно прав для удаления комментария: " + commentId);
        }
    }

    /**
     * Обновление текста комментария пользователя
     *
     * @param adId id объявления, к которому был опубликован комментарий
     * @param commentId id комментария
     * @param text DTO объект, представляющий обновленный комментарий
     * @return обновленный CommentDTO
     * @throws CommentNotFoundException   если комментария с переданным Id не существует
     * @throws UserNotAuthorizedException если пользователь не является автором комментария и не админ
     */
    @Override
    public CommentDTO updateComment(int adId,
                                 int commentId,
                                 CreateOrUpdateCommentDTO text) {
        logger.info("Comment updateComment is running");
        checkIfCommentExist(commentId);
        if (isCurrentUserAuthorized(commentId)) {
            Comment commentToUpdate = commentRepo.findById(commentId).get();
            commentToUpdate.setText(text.getText());
            commentToUpdate.setCreatedAt(LocalDateTime.now());
            return commentMapper.toDto(commentRepo.save(commentToUpdate));
        }
        throw new UserNotAuthorizedException("У пользователя c id: " + getCurrentUser().getId() +
                " недостаточно прав для редактирования комментария: " + commentId);
    }
//        final var comment = commentRepo.findById(Math.toIntExact(commentId)).orElseThrow();
//        comment.setText(String.valueOf(text));
//        return commentRepo.save(comment);
//    }

    /**
     * Метод, который возвращает текущего пользователя
     * @return User
     * @see User
     */
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) ((org.springframework.security.core.Authentication) authentication).getPrincipal()).getUsername();
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " is not found"));
    }

    /**
     * Метод, который определяет, уполномочен ли текущий пользователь осуществлять
     * редактирование и удаление комментария (пользователь вправе делать это
     * в случае, если он является автором данного комментария либо имеет роль админ).
     *
     * @param commentId Id комментария
     * @return true, если текущий пользователь - это автор комментария (или админ),
     * false - если нет.
     * @see Role
     */
    private boolean isCurrentUserAuthorized(int commentId) {
        User currentUser = getCurrentUser();
        User commentAuthor = userRepo.findById(commentRepo.findAuthorIdById(commentId)
                        .orElseThrow(() -> new CommentNotFoundException("Комментарий с id: " + commentId + " не найден")))
                .orElseThrow(() -> new UserNotFoundException("Пользователь, написавший комментария с id: " + commentId + " не найден"));
        return currentUser.equals(commentAuthor) || currentUser.getRole() == Role.ADMIN;
    }

    private void checkIfCommentExist(int commentId) {
        if (!commentRepo.existsById(commentId)) {
            throw new CommentNotFoundException("Комментарий с id: " + commentId + " не найден");
        }
    }
}