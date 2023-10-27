package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.Comments;
import ru.skypro.homework.dto.comment.CreateOrUpdateComment;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Users;
import ru.skypro.homework.exceptions.AccessErrorException;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.CommentNotFoundException;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UsersRepository usersRepository;

    public CommentServiceImpl(CommentRepository commentRepository, AdsRepository adsRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.usersRepository = usersRepository;
    }

    private boolean isAdminOrOwnerComment(Authentication authentication, String ownerComment) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");

        boolean isOwnerComment = authentication.getName().equals(ownerComment);

        return isAdmin || isOwnerComment;

    }

    @Override
    public Comments getComments(Integer adId) {
        List<CommentDTO> commentDTOList = commentRepository.findAllCommentsByAdId(adId)
                .stream()
                .map(CommentDTO::fromComment)
                .collect(Collectors.toList());
        return new Comments(commentDTOList.size(), commentDTOList);
    }

    @Override
    public void addComment(Integer id, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        String username = authentication.getName();

        Ad getAd = adsRepository.findAdByPk(id).orElseThrow(AdNotFoundException::new);
        Users meUsers = usersRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        Comment newComment = new Comment();

        newComment.setUsers(meUsers);
        newComment.setAd(getAd);
        newComment.setText(createOrUpdateComment.getText());
        newComment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (!adId.equals(findComment.getAd().getPk())) {
            throw new CommentNotFoundException();
        } else {
            if (isAdminOrOwnerComment(authentication, findComment.getUsers().getUsername())) {
                commentRepository.delete(findComment);
            }
            else {
                throw new AccessErrorException();
            }
        }
    }

    @Override
    public void updateComment(Integer adId, Integer commentId, CreateOrUpdateComment createOrUpdateComment, Authentication authentication) {
        Comment findComment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if (!adId.equals(findComment.getAd().getPk())) {
            throw new CommentNotFoundException();
        } else {

            if (isAdminOrOwnerComment(authentication, findComment.getUsers().getUsername())) {
                findComment.setText(createOrUpdateComment.getText());
                findComment.setCreatedAt(LocalDateTime.now());
                commentRepository.save(findComment);
            }
            else {
                throw new AccessErrorException();
            }

        }
    }
}
