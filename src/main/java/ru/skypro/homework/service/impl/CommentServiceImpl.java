package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdEntity;
import ru.skypro.homework.model.CommentEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    private final AdRepository adRepository;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper, AdRepository adRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.adRepository = adRepository;
        this.userService = userService;
    }

    @Override
    public Comments getComments(Integer id) {

        List<Comment> comments = commentRepository.findByAdId(id).stream()
                .map(comment -> commentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());

        return new Comments(comments.size(), comments);
    }

    @Override
    public Comment addComment(Integer id, CreateOrUpdateComment createOrUpdateComment, String username) {
        AdEntity ad = adRepository.findById(id).get();
        UserEntity author = userService.checkUserByUsername(username);

        CommentEntity comment = new CommentEntity();
        comment.setAd(ad);
        comment.setAuthor(author);
        comment.setCreatedAt(System.currentTimeMillis());
        comment.setText(createOrUpdateComment.getText());

        return commentMapper.mapToCommentDto(comment);
    }
}
