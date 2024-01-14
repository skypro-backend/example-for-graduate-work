package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.comments.CommentDto;
import ru.skypro.homework.dto.comments.CommentsDto;
import ru.skypro.homework.dto.comments.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    private final CommentMapper mapper;

    private final AdRepository adRepository;

    private final UserRepository userRepository;


    @Override
    public CommentsDto getComments(int id) {

        List<Comment> cdl = adRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found")).getComments();

        if (!cdl.isEmpty()) {
            List<CommentDto> result = cdl.stream()
                    .map(mapper::commentToDto)
                    .collect(Collectors.toList());
            return new CommentsDto(result.size(), result);
        } else {
            return new CommentsDto(0, Collections.emptyList());
        }

    }

    @Override
    public CommentDto addComment(int id, CreateOrUpdateComment text, String userName) {

        Ad ad = adRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Ad with id " + id + " not found"));
        UserEntity author = userRepository.findByUserName(userName).orElseThrow(() -> new NoSuchElementException("User with userName: " + userName + " not found"));
        Comment comment = Comment.builder()
                .author(author)
                .createdAt(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)))
                .text(text.getText())
                .ad(ad)
                .build();
        repository.save(comment);

        return mapper.commentToDto(comment);
    }

    @Override
    public void deleteComment(int adId, int commentId, String userName) {

        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new NoSuchElementException("Ad with id " + adId + " not found"));
        Comment comment = repository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment with id " + commentId + " not found"));
        if (author.equals(comment.getAuthor()) || author.getRole().equals(Role.ADMIN)) {
            repository.delete(comment);
        } else {
            throw new NoSuchElementException("Author with id " + author.getId() + " haven't ad with id " + ad.getId());
        }
    }

    @Override
    public CommentDto updateComment(int adId, int commentId, CreateOrUpdateComment text, String userName) {

        UserEntity author = userRepository.findByUserName(userName).orElseThrow();
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new NoSuchElementException("Ad with id " + adId + " not found"));
        Comment comment = repository.findById(commentId).orElseThrow(() -> new NoSuchElementException("Comment with id " + commentId + " not found"));
        if (author.equals(comment.getAuthor()) || author.getRole().equals(Role.ADMIN)) {
            comment.setText(text.getText());
            repository.save(comment);
            return mapper.commentToDto(comment);
        } else {
            throw new NoSuchElementException("Author with id " + author.getId() + " haven't ad with id " + ad.getId());
        }
    }
}
