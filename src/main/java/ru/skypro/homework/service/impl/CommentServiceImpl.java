package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Advert;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdvertService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final AdvertService advertService;
    private final UserService userService;
    private final CommentRepository repository;
    private final CommentMapper mapper;

    /**
     * getUser() is a method used to get the current user
     * @author radyushinaalena
     */
    private User getUser() {
        return userService.find();
    }


    /**
     * getAdvert(int advertId) is a method used to get an ad
     * @author radyushinaalena
     */
    private Advert getAdvert(int advertId) {
        return advertService.find(advertId);
    }


    /**
     * find(int commentId) is a public method used to search for a comment
     * @author radyushinaalena
     */
    @Override
    public Comment find(int commentId) {
        return repository.findById(commentId)
                .orElseThrow(EntityNotFoundException::new);
    }


    /**
     * createComment(int advertId, CreateOrUpdateCommentDto createOrUpdateCommentDto) is a public method used to create a comment
     * @author radyushinaalena
     */
    @Override
    public CommentDto createComment(int advertId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        var advert = getAdvert(advertId);
        var author = getUser();
        var createTime = LocalDateTime.now();
        var comment = mapper.commentDtoToComment(createOrUpdateCommentDto);
        comment.setAdvert(advert);
        comment.setAuthor(author);
        comment.setCreatedAt(createTime);
        repository.save(comment);
        return mapper.commentToCommentDto(comment);
    }


    /**
     * getAllCommentsAdvert(int advertId) is a public method used to get all the comments of an ad
     * @author radyushinaalena
     */
    @Override
    public CommentsDto getAllCommentsAdvert(int advertId) {
        var commentList = repository.findByAdvertId(advertId);
        var commentDtoList = mapper.commentsToCommentDtos(commentList);
        return new CommentsDto(commentDtoList);
    }


    /**
     * updateComment(int advertId, int commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) is a public method used to update an ad comment
     * @author radyushinaalena
     */
    @Override
    public CommentDto updateComment(int advertId, int commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        var comment = find(commentId);
        if (comment.getAdvert().getId() != advertId) {
            throw new RuntimeException();
        }
        mapper.updateCommentFromDto(createOrUpdateCommentDto, comment);
        repository.save(comment);
        return mapper.commentToCommentDto(comment);
    }


    /**
     * deleteComment(int advertId, int commentId) is a public method used to delete an ad comment
     * @author radyushinaalena
     */
    @Override
    public void deleteComment(int advertId, int commentId) {
        var comment = find(commentId);
        if (comment.getAdvert().getId() != advertId) {
            throw new RuntimeException();
        }
        repository.delete(comment);
    }
}