package ru.skypro.homework.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exceptions.BlankFieldException;
import ru.skypro.homework.exceptions.MissingAdException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.helper.AuthenticationCheck;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final AdEntityRepository adEntityRepository;
    private final CommentEntityRepository commentEntityRepository;
    private final CommentMapper commentMapper;
    private final AuthenticationCheck authenticationCheck;
    private final UserEntityRepository userEntityRepository;

    public CommentServiceImpl(AdEntityRepository adEntityRepository, CommentEntityRepository commentEntityRepository, CommentMapper commentMapper, AuthenticationCheck authenticationCheck, UserEntityRepository userEntityRepository) {
        this.adEntityRepository = adEntityRepository;
        this.commentEntityRepository = commentEntityRepository;
        this.commentMapper = commentMapper;
        this.authenticationCheck = authenticationCheck;
        this.userEntityRepository = userEntityRepository;
    }

    /**
     * Trying to find the list of comments for each ad, using
     * {@code commentEntityRepository.findByAdId_Id(adId)};
     * <br>
     * and, if there are some comment use
     * <br> {@link CommentMapper#commentEntityListToCommentList(List)}
     * @param adId Integer;
     * @return Comments.builder().results(comments).count(comments.size()).build();
     */
    @Override
    public Comments getComments(Integer adId) {

        List<Comment> comments = commentMapper
                .commentEntityListToCommentList(commentEntityRepository.findByAdEntity_id(adId));
        return Comments.builder()
                .results(comments)
                .count(comments.size())
                .build();
    }

    /**
     * Addition new comment for ad;
     * <br>
     * <br> checking text of new comment and user's data:
     * {@code adEntityRepository.findById(adId)} and
     * {@code userEntityRepository.findByUsername(userDetails.getUsername())};
     * <br>
     * <br> after use
     * <br> {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)},
     * next build new comment and using
     * <br> {@link CommentMapper#commentEntityToComment(CommentEntity)}, than
     * <br> {@code commentEntityRepository.save(commentEntity)};
     * @param adId Integer;
     * @param createOrUpdateComment CreateOrUpdateComment;
     * @param userDetails CustomUserDetails;
     * @return commentMapper.commentEntityToComment(commentEntityRepository.save(newComment));
     */
    @Override
    public Comment addComment(Integer adId, CreateOrUpdateComment createOrUpdateComment, CustomUserDetails userDetails) {
        if (createOrUpdateComment.getText() == null) {
            throw new BlankFieldException("Пустое поле обновленного комментария");
        }

        AdEntity adEntity = adEntityRepository.findById(adId).orElseThrow(() ->
                new MissingAdException("The ad with the specified id is missing "));
        UserEntity userForAuthCheck = userEntityRepository.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new MissingUserException ("User has not been found"));
        authenticationCheck.accessCheck(userDetails, userForAuthCheck);

        CommentEntity newComment = CommentEntity.builder()
                .text(createOrUpdateComment.getText())
                .createdAt(Instant.now())
                .userEntity(userForAuthCheck)
                .adEntity(adEntity)
                .build();

        return commentMapper.commentEntityToComment(commentEntityRepository.save(newComment));
    }

    /**
     * Removing comment using
     * <br> {@code adEntityRepository.findById(adId)};
     * <br> also {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)},
     * <br> and
     * <br> {@link CommentEntityRepository#deleteById(Integer)};
     * @param adId Integer;
     * @param commentId Integer;
     * @param userDetails CustomUserDetails;
     */
    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer commentId, CustomUserDetails userDetails) {

        CommentEntity commentEntity = commentEntityRepository.findById(commentId).orElseThrow(() -> new MissingAdException(" The comment with the specified id is missing"));
        authenticationCheck.accessCheck(userDetails, commentEntity.getUserEntity());

        commentEntityRepository.deleteById(commentId);
    }

    /**
     * Updating comment using
     * <br> {@code adEntityRepository.findById(adId)} (for checking, if id exists),
     * <br> also
     * <br> {@link AuthenticationCheck#accessCheck(CustomUserDetails, UserEntity)},
     * <br> {@code commentEntityRepository.save(commentEntity)};
     * <br> and next
     * <br> {@link CommentMapper#commentEntityToComment(CommentEntity)};
     * @param adId Integer;
     * @param commentId Integer;
     * @param createOrUpdateComment CreateOrUpdateComment;
     * @param userDetails CustomUserDetails;
     * @return commentMapper.commentEntityToComment(commentEntity);
     */
    @Override
    public Comment updateComment(Integer adId, Integer commentId,CreateOrUpdateComment createOrUpdateComment,CustomUserDetails userDetails) {

        CommentEntity commentEntity = commentEntityRepository.findById(commentId).orElseThrow(() -> new MissingAdException(" The comment with the specified id is missing"));
        authenticationCheck.accessCheck(userDetails, commentEntity.getUserEntity());

        commentEntity.setText(createOrUpdateComment.getText());
        commentEntityRepository.save(commentEntity);

        return commentMapper.commentEntityToComment(commentEntity);
    }
}
