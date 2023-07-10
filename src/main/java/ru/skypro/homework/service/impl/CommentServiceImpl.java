package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.CommentRepository;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final AdsService adsService;

    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, AdsService adsService, UserService userService) {
        this.commentRepository = commentRepository;
        this.adsService = adsService;
        this.userService = userService;
    }

    @Override
    public ResponseWrapperComment getUserComments(int userId) {
        CommentDTO[] comments = (CommentDTO[]) userService.findUserById(userId).getComments().stream()
                .map(CommentDTO::fromComment)
                .toArray();
        int count = comments.length;

        return new ResponseWrapperComment(count, comments);

    }

    @Override
    public CommentDTO addComment(int adId, CommentDTO commentDTO) {
        Ad ad = adsService.getAdById(adId);
        Comment comment = new Comment(ad.getUser(), ad, commentDTO.getCreateAt(), commentDTO.getText());
        commentRepository.saveAndFlush(comment);

        return commentDTO;
    }
//
//
//    }
}
