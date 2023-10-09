package ru.skypro.homework.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import ru.skypro.homework.entity.Comment;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.exception.custom_exception.AdNotFoundException;
import ru.skypro.homework.exception.custom_exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.projection.CommentView;
import ru.skypro.homework.projection.Comments;
import ru.skypro.homework.projection.CreateOrUpdateComment;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.user.UserService;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;
    private final UserService userService;


    @Override
    public Comments getAllCommentsByAdId(Integer id) {
        List<Comment> list = commentRepository.findAllByAd_Pk(id);
        return new Comments()
                .setResults(list.stream()
                        .map(CommentMapper::toView)
                        .collect(Collectors.toList()))
                .setCount(list.size());
    }

    @Override
    public CommentView createComment(Integer id, CreateOrUpdateComment comment,Authentication authentication) {
        return CommentMapper.toView(commentRepository.save(new Comment()
                .setCreatedAt(Instant.now())
                .setAd(adRepository.findById(id).orElseThrow(AdNotFoundException::new))
                .setText(comment.getText())
                .setUser(UserMapper.fromDTO(userService.getCurrentUser(authentication)))));
    }

    @Override
    public void deleteComment(Integer commentId,Integer adId) {
        commentRepository.deleteCommentByPkAndAd_Pk(commentId, adId);
    }

    @Override
    public CommentView updateComment(Integer commentId,Integer adId, CreateOrUpdateComment comment) {
        Comment commentResult = commentRepository.findByPkAndAd_Pk(commentId, adId).orElseThrow(CommentNotFoundException::new);
        commentResult.setText(comment.getText())
                .setCreatedAt(Instant.now());
        return CommentMapper.toView(commentRepository.save(commentResult));
    }

    @Override
    public CommentDTO findById(Integer id) {
        return commentRepository.findById(id).map(CommentMapper::fromComment).orElseThrow(CommentNotFoundException::new);
    }
}