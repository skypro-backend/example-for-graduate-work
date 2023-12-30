package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repositories.AdRepository;
import ru.skypro.homework.repositories.CommentRepository;
import ru.skypro.homework.security.GetAuthentication;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentDTO addComment(long id,
                                 CreateOrUpdateCommentDTO createOrUpdateCommentDto,
                                 Authentication authentication) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Объявление с ID " + id + " не найдено"));
        Comment comment = new Comment();
        comment.setText(createOrUpdateCommentDto.getText());
        comment.setAd(ad);
        comment.setCreatedAt(LocalDateTime.now());
        User user = new GetAuthentication().getAuthenticationUser(authentication.getName());
        comment.setAuthor(user);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public CommentsDTO getComments(long id) {
        List<Comment> commentList = commentRepository.findCommentsByAdId(id);
        CommentsDTO commentsDto = new CommentsDTO();
        commentsDto.setCount(commentList.size());
        commentsDto.setResults(commentList.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList()));
        return commentsDto;
    }

    @Override
    @Transactional
    public void deleteComment(long idAd,
                              long idComment,
                              Authentication authentication){
        Comment comment = commentRepository.findById(idComment).orElseThrow(() ->
                new NotFoundException("Комментарий с ID" + idComment + "не найден"));
        checkPermit(comment, authentication);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public CommentDTO updateComment(long idAd,
                                    long idComment,
                                    CreateOrUpdateCommentDTO createOrUpdateCommentDto,
                                    Authentication authentication) throws AccessDeniedException {
        Comment comment = commentRepository.findById(idComment).orElseThrow(() ->
                new NotFoundException("Комментарий с ID" + idComment + "не найден"));
        checkPermit(comment, authentication);
        comment.setText(createOrUpdateCommentDto.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public void checkPermit(Comment comment, Authentication authentication){
        if (!comment.getAuthor().getEmail().equals(authentication.getName()) && !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            throw new org.springframework.security.access.AccessDeniedException("Вы не можете редактировать или удалять чужое объявление");
        }
    }
}
