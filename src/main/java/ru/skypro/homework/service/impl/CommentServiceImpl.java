package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateCommentDto;
import ru.skypro.homework.exception.ForbiddenException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final CommentMapper commentMapper;

    @Override
    public CommentsDto getAdComments(Integer adId) {
        List<CommentDto> comments = commentRepository.findAllByAdId(adId)
                .stream()
                .map(commentMapper::toDto)
                .toList();
        return new CommentsDto(comments.size(), comments);
    }

    @Override
    @Transactional
    public CommentDto addComment(String email, Integer adId, CreateOrUpdateCommentDto dto) {
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Ad ad = adRepository.findById(adId)
                .orElseThrow(() -> new NotFoundException("Объявление не найдено"));
        Comment comment = new Comment();
        comment.setText(dto.text());
        comment.setAuthor(author);
        comment.setAd(ad);
        comment.setCreatedAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    @Transactional
    public void removeComment(String email, Integer id, Integer adId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        if(!comment.getAuthor().getEmail().equals(email)){
            throw new ForbiddenException("Недостаточно прав на удаление комментария");
        }
        if(!comment.getAd().getId().equals(adId)){
            throw new ForbiddenException("Комментарий не принадлежит указанному объявлению");
        }
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public CommentDto updateComment(String email, Integer id, Integer adId,
                                    CreateOrUpdateCommentDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Комментарий не найден"));
        if(!comment.getAuthor().getEmail().equals(email)){
            throw new ForbiddenException("Недостаточно прав на редактирование комментария");
        }
        if(!comment.getAd().getId().equals(adId)){
            throw new ForbiddenException("Комментарий не принадлежит указанному объявлению");
        }
        comment.setText(dto.text());
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }
}
