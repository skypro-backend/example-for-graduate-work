package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.comment.Comment;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.NoAccessToAdException;
import ru.skypro.homework.exception.NoAccessToCommentException;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.mapping.CommentMapper;
import ru.skypro.homework.service.CommentService;

import java.time.Instant;

@Slf4j
@Service
public class CommentServiceImpl  implements CommentService {

    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(AdRepository adRepository, CommentMapper commentMapper, CommentRepository commentRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;

    }



    @Override
    public Comments getCommentsOfOneAd(int adId) {
        if (adRepository.getReferenceById(adId) != null) {
            Ad adToRetrieveCommentsFrom = adRepository.getReferenceById(adId);
            Comments retrievedComments = commentMapper.adCommentsToCommentsDTO(adToRetrieveCommentsFrom);
            return retrievedComments;
        } else {
            throw new NoAccessToAdException();
        }
    }

    @Override
    public Comment addCommentToAd(CreateOrUpdateComment commentToAdd, int adId) {
        if (adRepository.getReferenceById(adId) != null) {
            Ad adToAddComment = adRepository.getReferenceById(adId);
            CommentEntity mapperToDto = commentMapper.createOrUpdateCommentDtoToCommentEntity(commentToAdd);
            mapperToDto.setUserRelated(adToAddComment.getUserRelated());
            mapperToDto.setAdRelated(adToAddComment);
            mapperToDto.setCreatedAt(Instant.now().toEpochMilli());
            commentRepository.save(mapperToDto);
            return commentMapper.commentEntityToCommentDto(mapperToDto);
        } else {
            throw new NoAccessToAdException();
        }
    }

    @Override
    public boolean patchCommentByIdAndAdId(int adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, String username) {
        Ad adFound = adRepository.getReferenceById(adId);
        CommentEntity commentFound = commentRepository.findByAdRelatedAndId(adFound, commentId);
        UserEntity userCommented = commentFound.getUserRelated();
        UserEntity authorizedUser = userRepository.findByUsername(username);
        Role authorizedUserRole = authorizedUser.getRole();

        if ((userCommented.equals(authorizedUser) || authorizedUserRole == Role.ADMIN)) {
            commentFound.setText(createOrUpdateComment.getText());
            commentRepository.save(commentFound);
            return true;
        } else {
            throw new NoAccessToCommentException();
        }
    }

    @Override
    public boolean deleteCommentByIdAndAdId(int adId, Integer commentId, String username) {
        Ad adFound = adRepository.getReferenceById(adId);
        CommentEntity commentFound = commentRepository.findByAdRelatedAndId(adFound, commentId);
        UserEntity userWhoCommented = commentFound.getUserRelated();
        UserEntity authorizedUser = userRepository.findByUsername(username);
        Role authorizedUserRole = authorizedUser.getRole();
        if ((userWhoCommented.equals(authorizedUser)) || authorizedUserRole == Role.ADMIN) {
            commentRepository.deleteById(commentFound.getId());
            commentRepository.flush();
            return true;
        } else {

            throw new NoAccessToCommentException();
        }
    }
}
