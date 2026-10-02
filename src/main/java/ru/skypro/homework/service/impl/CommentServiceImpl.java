package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.api.dto.Comment;
import ru.skypro.homework.api.dto.Comments;
import ru.skypro.homework.api.dto.CreateOrUpdateComment;
import ru.skypro.homework.db.model.AdModel;
import ru.skypro.homework.db.model.CommentModel;
import ru.skypro.homework.db.model.UserModel;
import ru.skypro.homework.db.model.UserRole;
import ru.skypro.homework.db.repository.AdRepository;
import ru.skypro.homework.db.repository.CommentRepository;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Comments getComments(Integer adId) {
        AdModel ad = getAd(adId);
        return commentMapper.toComments(commentRepository.findAllByAd(ad));
    }

    @Override
    @Transactional
    public Comment addComment(String username, Integer adId, CreateOrUpdateComment comment) {
        UserModel author = userService.getCurrentUserModel(username);
        AdModel ad = getAd(adId);

        CommentModel commentModel = commentMapper.toModel(comment);
        commentModel.setAd(ad);
        commentModel.setAuthor(author);
        commentModel.setCreatedAt(LocalDateTime.now());
        return commentMapper.toDto(commentRepository.save(commentModel));
    }

    @Override
    @Transactional
    public void deleteComment(String username, Integer adId, Integer commentId) {
        UserModel currentUser = userService.getCurrentUserModel(username);
        CommentModel comment = getComment(adId, commentId);
        checkCanModify(currentUser, comment);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public Comment updateComment(String username, Integer adId, Integer commentId, CreateOrUpdateComment comment) {
        UserModel currentUser = userService.getCurrentUserModel(username);
        CommentModel commentModel = getComment(adId, commentId);
        checkCanModify(currentUser, commentModel);

        commentModel.setText(comment.getText());
        return commentMapper.toDto(commentRepository.save(commentModel));
    }

    private AdModel getAd(Integer adId) {
        return adRepository.findById(adId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    private CommentModel getComment(Integer adId, Integer commentId) {
        AdModel ad = getAd(adId);
        return commentRepository.findByIdAndAd(commentId, ad)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    private void checkCanModify(UserModel currentUser, CommentModel comment) {
        boolean isAdmin = currentUser.getRole() == UserRole.ADMIN;
        boolean isAuthor = comment.getAuthor().getId().equals(currentUser.getId());
        if (!isAdmin && !isAuthor) {
            throw new AccessDeniedException("Only author or admin can modify comment");
        }
    }
}
