package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperCommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdvertRepository;
import ru.skypro.homework.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdvertRepository advertRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, AdvertRepository advertRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.advertRepository = advertRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDto create(Integer advertId, String text) {
        Comment comment = new Comment();
        comment.setText(text);
        Optional<Advert> adv = advertRepository.findById(advertId);
        comment.setAdvert(adv.get());
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    public void delete(Integer advertId, Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.get().getAdvert().getId() == advertId) {
            commentRepository.delete(comment.get());
        }
    }

    public CommentDto update(Integer advertId, Integer commentId, CommentDto commentDto) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.get().getAdvert().getId() == advertId) {
            comment = Optional.ofNullable(CommentMapper
                    .INSTANCE.commentDtoToComment(commentDto));
            commentRepository.save(comment.get());
        }
        return CommentMapper.INSTANCE.commentToCommentDto(comment.get());
    }

    public ResponseWrapperCommentDto findAll(Integer advertId) {
        List<Comment> comments = commentRepository.findAllByAdvert_Id(advertId);
        ResponseWrapperCommentDto wrapperCommentDto = new ResponseWrapperCommentDto();
        wrapperCommentDto.setCount(comments.size());
        wrapperCommentDto.setResults(commentListToCommentDtoList(comments));
        return wrapperCommentDto;
    }

    private List<CommentDto> commentListToCommentDtoList(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(CommentMapper.INSTANCE.commentToCommentDto(comment));
        }
        return commentDtos;
    }
}
