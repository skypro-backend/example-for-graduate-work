package ru.skypro.homework.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.user.MyUserDetails;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.ResponseWrapperComment;
import ru.skypro.homework.dto.enums.Role;
import ru.skypro.homework.exception.notFound.AdsNotFoundException;
import ru.skypro.homework.exception.notFound.CommentNotFoundException;
import ru.skypro.homework.exception.NotAuthorizedException;
import ru.skypro.homework.exception.notFound.UserNotFoundException;
import ru.skypro.homework.model.AdsModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final AdsRepository adsRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService( AdsRepository adsRepository, CommentRepository commentRepository, UserRepository userRepository ) {
        this.adsRepository = adsRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public ResponseWrapperComment getComments( Integer id ) {
        ResponseWrapperComment rwc = new ResponseWrapperComment();
        AdsModel foundedAds = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        List<Comment> comments = foundedAds.getComments()
                .stream()
                .map(Comment::fromModel)
                .collect(Collectors.toList());
        rwc.setCount(comments.size());
        rwc.setResults(comments);
        return rwc;
    }

    public Comment addComment( Integer id, Comment comment, Authentication authentication ) {
        UserModel user = ((MyUserDetails) authentication.getPrincipal()).toModel();

        if (!authentication.isAuthenticated()) {
            throw new AccessDeniedException("Недостаточно прав для перехода на страницу!");
        }
        if (user == null) {
            throw new NotAuthorizedException();
        }

        AdsModel founded = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);
        CommentModel commentModel = comment.toModel();

        commentModel.setUser(user);
        commentModel.setAds(founded);
        commentModel.setCreatedAt(Instant.now());
        founded.getComments().add(commentModel);
        adsRepository.save(founded);
        return Comment.fromModel(commentModel);
    }

    public Comment updateComment( Integer adId, Integer commentId, Comment comment, Authentication authentication ) {
        UserModel currentUser = userRepository.findByUsername(authentication.getName()).orElseThrow(UserNotFoundException::new);
        if (currentUser == null) {
            throw new NotAuthorizedException();
        }
        AdsModel neededAds = adsRepository.findById(adId).orElseThrow(AdsNotFoundException::new);
        CommentModel prevComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (currentUser.getRole().equals(Role.USER)) {
            if (prevComment.getUser().getPk().equals(currentUser.getPk())) {
                CommentModel newComment = comment.toModel();
                newComment.setAds(neededAds);
                newComment.setId(commentId);
                newComment.setUser(prevComment.getUser());
                commentRepository.save(newComment);
                return Comment.fromModel(newComment);
            } else {
                throw new AccessDeniedException("Недостаточно прав для изменения комментария другого пользователя!");
            }
        } else {
            CommentModel newComment = comment.toModel();
            newComment.setAds(neededAds);
            newComment.setId(commentId);
            newComment.setUser(prevComment.getUser());
            commentRepository.save(newComment);
            return Comment.fromModel(newComment);
        }
    }

    public void deleteComment( Integer adId, Integer commentId, Authentication authentication ) {
        UserModel currentUser = ((MyUserDetails) authentication.getPrincipal()).toModel();
        if (currentUser == null) {
            throw new NotAuthorizedException();
        }
        CommentModel currentComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (currentUser.getRole().equals(Role.USER)) {
            if (currentComment.getUser().getPk().equals(currentUser.getPk())) {
                commentRepository.deleteCommentModelByIdAndAds_Id(commentId, adId);
            } else {
                throw new AccessDeniedException("Недостаточно прав для удаления комментария другого пользователя!");
            }
        } else {
            commentRepository.deleteCommentModelByIdAndAds_Id(commentId, adId);
        }
    }
}
