package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.Exceptions.FindNoEntityException;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.*;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.*;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final UserService userService;
    private final AdService adService;
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    @Override
    public CommentsDto getComments(int id) {
        List<CommentDto> result = new LinkedList<>();
        commentRepository.findAllByAd_Pk(id).forEach(entity -> result.add(mapper.entityToCommentDto(entity)));
        return new CommentsDto(result.size(), result);
    }

    @Override
    public CommentDto add(int id, CreateOrUpdateCommentDto comment, String name) {
        Comment entity = mapper.createСommentToEntity(comment, adService.getEntity(id), userService.getEntity(name));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public void delete(int commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto update (int commentId, CommentDto comment, String email) {
        Comment entity = getEntity(commentId);
        entity.setText(comment.getText() + "(отредактировал(а) " + userService.getEntity(email).getFirstName() +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(" dd MMMM yyyy в HH:mm:ss)")));
        return mapper.entityToCommentDto(commentRepository.save(entity));
    }

    @Override
    public Comment getEntity(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new FindNoEntityException("There is no comment with the specified id"));
    }
}
