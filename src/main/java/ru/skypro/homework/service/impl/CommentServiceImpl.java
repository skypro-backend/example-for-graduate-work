package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final AdsService adsService;

    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, AdsService adsService, UserService userService) {
        this.commentRepository = commentRepository;
        this.adsService = adsService;
        this.userService = userService;
    }
    /**
     * Получения комментария авторизованного пользователя
     */
    @Override
    public ResponseWrapperComment getUserComments(int adsId) {
        Ad ad = adsService.getAdById(adsId);
        List<Comment> comments = commentRepository.findCommentByAd(ad);
        List<CommentDTO> commentsDTOList = comments.stream()
                                                      .map(this::commentToCommentDTO)
                                                      .collect(Collectors.toList());
        return new ResponseWrapperComment(commentsDTOList.size(), commentsDTOList);

    }


    public CommentDTO commentToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getUser().getId(),
                comment.getUser().getImage() == null ? null : "/users/avatar/" + comment.getUser().getId(),
                comment.getUser().getFirstName(),
                comment.getTime(),
                comment.getId(),
                comment.getText());
    }
    /**
     * Добавления комментария
     */
    @Override
    public CommentDTO addComment(int adId, CommentDTO commentDTO) {
        Ad ad = adsService.getAdById(adId);
        Comment comment = new Comment(ad.getUser(), ad, commentDTO.getCreateAt(), commentDTO.getText());
        commentRepository.saveAndFlush(comment);
        return commentDTO;
    }
    /**
     * Обновления текста комментария
     */
    @Override
    public CommentDTO updateComment(int adId, int commentId, CommentDTO commentDTO) {
        Comment comment;
        try {
            comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        } catch (CommentNotFoundException e) {
            throw new RuntimeException(e);
        }
        comment.setText(commentDTO.getText());
        comment.setTime(commentDTO.getCreateAt());

        commentRepository.saveAndFlush(comment);

        return commentDTO;
    }
    /**
     * Удалить комментарий
     */
    @Override
    public void deleteComment(int adId, int commentId) {
        commentRepository.deleteById(commentId);
    }
//
//
//    }
}
