package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.config.GetAuthentication;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentsService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentsService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public CommentsDto getComments(long id) {
        List<Comment> commentList = commentRepository.findCommentsByAdId(id);
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setCount(commentList.size());
        commentsDto.setResults(commentList.stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList()));
        return commentsDto;
    }

    @Override
    public CommentDto addComment(long id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID " + id + " не найдено"));
        Comment comment = new Comment();
        comment.setText(createOrUpdateComment.getText());
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        comment.setAuthor(user);
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(long adId, long commentId, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Комментарий с ID" + commentId + "не найден"));
        checkPermit(comment, authentication);
        commentRepository.delete(comment);

    }

    @Override
    @Transactional
    public CommentDto updateComment(long adId, long commentId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new NotFoundException("Комментарий с ID" + commentId + "не найден"));
        checkPermit(comment, authentication);
        comment.setText(createOrUpdateComment.getText());
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    public void checkPermit(Comment comment, Authentication authentication){
        if (!comment.getAuthor().getEmail().equals(authentication.getName()) && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new AccessDeniedException("Вы не можете редактировать или удалять чужое объявление") ;
        }
    }
}
