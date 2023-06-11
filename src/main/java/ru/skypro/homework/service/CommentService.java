package ru.skypro.homework.service;

import lombok.extern.slf4j.Slf4j;
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

/**
 * Service for creating, deleting, updating, finding comments from {@link CommentRepository}
 */
@Service
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final AdvertRepository advertRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, AdvertRepository advertRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.advertRepository = advertRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * method for creating comment and save him into {@link CommentRepository}
     * @param advertId
     * @param text
     * @return commentDto
     */
    public CommentDto create(Integer advertId, String text) {
        log.info("creating comment:" + text + " for advert with id: " + advertId);
        Comment comment = new Comment();
        comment.setText(text);
        Optional<Advert> adv = advertRepository.findById(advertId);
        comment.setAdvert(adv.get());
        comment.setAuthor(adv.get().getAuthor());
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    /**
     * method for deleting comment from db by advertId and commentId
     * @param advertId
     * @param commentId
     */
    public void delete(Integer advertId, Integer commentId) {
        log.info("deleting comment with id: " + commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.get().getAdvert().getId() == advertId) {
            commentRepository.delete(comment.get());
        }
    }

    /**
     * method for updating comment
     * @param advertId
     * @param commentId
     * @param commentDto
     *
     */
    public CommentDto update(Integer advertId, Integer commentId, CommentDto commentDto) {
        log.info("updating comment with id: " + advertId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.get().getAdvert().getId() == advertId) {
            comment = Optional.ofNullable(CommentMapper
                    .INSTANCE.commentDtoToComment(commentDto));
            commentRepository.save(comment.get());
        }
        return CommentMapper.INSTANCE.commentToCommentDto(comment.get());
    }

    /**
     * method for getting number and list of comments by advertId
     * @param advertId
     * @return responseWrapperCommentDto contains number of comments and list of comments
     */
    public ResponseWrapperCommentDto findAll(Integer advertId) {
        log.info("getting all comments for advert with id: " + advertId);
        List<Comment> comments = commentRepository.findAllByAdvert_Id(advertId);
        ResponseWrapperCommentDto wrapperCommentDto = new ResponseWrapperCommentDto();
        wrapperCommentDto.setCount(comments.size());
        wrapperCommentDto.setResults(commentListToCommentDtoList(comments));
        return wrapperCommentDto;
    }

    /**
     * private method for converting commentsList to list of commentsDto
     * @param comments
     */
    private List<CommentDto> commentListToCommentDtoList(List<Comment> comments) {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtos.add(CommentMapper.INSTANCE.commentToCommentDto(comment));
        }
        return commentDtos;
    }
}
