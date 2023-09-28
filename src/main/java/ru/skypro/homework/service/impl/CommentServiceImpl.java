package ru.skypro.homework.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService {
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(AdRepository adRepository,
                              CommentRepository commentRepository,
                              UserRepository userRepository) {

        this.adRepository = adRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentsDto getAllCommentsForAdById(Integer adPk) {
        List<Comment> comm = commentRepository.findCommentsByAd_Pk(adPk).orElseThrow(NoSuchElementException::new);
        List<CommentDto> commDto = new CommentsDto().fromCommentsList(comm);
        return new CommentsDto(commDto.size(), commDto);
    }

    @Override
    public CommentDto createNewComment(Integer adPk, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                       String username) {


        Ad ad = adRepository.findByPk(adPk).orElseThrow(NoSuchElementException::new);
        User newUser = userRepository.findUserByUsername(username)
                .orElseThrow(NoSuchElementException::new);

        Comment newComment = new Comment(newUser, ad, newUser.getImage(), newUser.getFirstName(), Instant.now(), createOrUpdateCommentDto.getText());
        commentRepository.save(newComment);
        return CommentDto.fromComment(newComment);
    }

    @Override
    public void deleteComment(Integer adPk, Integer commentPk) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Ad ad = adRepository.findByPk(adPk).orElseThrow(NoSuchElementException::new);
        Comment comment = commentRepository.findByPk(commentPk).orElseThrow(
                NoSuchElementException::new);

        if (ad.getUser().getUsername().equals(username) || ad.getUser().getRole().equals(Role.ADMIN.name())
                || comment.getUser().getUsername().equals(username)) {
            commentRepository.deleteById(commentPk);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public CommentDto updateComment(Integer adPk, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Comment oldComment = commentRepository.findByPk(commentId).orElseThrow(
                NoSuchElementException::new);
        Ad ad = adRepository.findByPk(adPk).orElseThrow(NoSuchElementException::new);
        Comment comment = commentRepository.findByPk(commentId).orElseThrow(
                NoSuchElementException::new);

        if (ad.getUser().getUsername().equals(username) || ad.getUser().getRole().equals(Role.ADMIN.name())
                || comment.getUser().getUsername().equals(username)) {
            oldComment.setText(createOrUpdateCommentDto.getText());
            commentRepository.save(oldComment);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return CommentDto.fromComment(oldComment);
    }

    public Integer getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        LocalDateTime now = LocalDateTime.now();

        return Integer.valueOf(sdfDate.format(now));
    }

}
