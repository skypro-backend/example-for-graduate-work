package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    /**
     * Getting ad comments using {@link AdRepository#findByPk(Integer)}, {@link CommentMapper#mapToListOfDTO(Ad)}
     * @param adId
     * @return Comments
     */
    @Override
    public Comments findAdComments(int adId) {
        Ad ad = adRepository.findByPk(adId);
        return commentMapper.mapToListOfDTO(ad);
    }

    /**
     * Creating comment using {@link UserRepository#findByUsername(String)}, {@link AdRepository#findByPk(Integer)},
     * {@link CommentMapper#createFromCreateOrUpdate(CreateOrUpdateComment, User, Ad)}, {@link CommentMapper#mapToDTO(Comment)}
     * @param adId
     * @param authentication
     * @param createOrUpdateComment
     * @return CommentDTO
     */
    @Override
    public CommentDTO createComment(int adId,
                                    CreateOrUpdateComment createOrUpdateComment,
                                    Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Ad ad = adRepository.findByPk(adId);
        Comment comment = commentMapper.createFromCreateOrUpdate(createOrUpdateComment, user, ad);
        return commentMapper.mapToDTO(comment);
    }

    /**
     * Deleting comment by ids of its ad and itself using {@link CommentRepository#deleteByAdPkAndPk(Integer, Integer)}
     * @param adId
     * @param commentId
     * @return
     */
    @Override
    public void deleteComment(int adId,
                              int commentId) {
        commentRepository.deleteByAdPkAndPk(adId, commentId);
    }

    /**
     * Editing comment by ids of its ad and itself using {@link AdRepository#findByPk(Integer)}, {@link CommentRepository#findByPk(Integer)},
     * {@link CommentMapper#updateFromCreateOrUpdate(Comment, CreateOrUpdateComment)}, {@link CommentMapper#mapToDTO(Comment)}
     * @param adId
     * @param commentId
     * @return CommentDTO
     */
    @Override
    public CommentDTO editComment(int adId,
                                  int commentId,
                                  CreateOrUpdateComment createOrUpdateComment) {
        Ad ad = adRepository.findByPk(adId);
        Comment comment = commentRepository.findByPk(commentId);
        comment = commentMapper.updateFromCreateOrUpdate(comment, createOrUpdateComment);
        return commentMapper.mapToDTO(comment);
    }

}
