package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CommentsDTO;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class CommentMapper {

    private final AdService adService;
    private final UserService userService;

    public CommentMapper(AdService adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setPk(comment.getCommentId());
        commentDTO.setAuthor(comment.getAuthor().getUserId());
        commentDTO.setAuthorFirstName(comment.getAuthor().getFirstName());
        commentDTO.setAuthorImage(comment.getAuthor().getAvatarFilePath());
        commentDTO.setDescription(comment.getCommentText());
        commentDTO.setCreatedAt(comment.getCreatedAt().atZone(ZoneId.systemDefault()).toEpochSecond());

        return commentDTO;
    }

    public Comment fromCommentDTO(CommentDTO commentDTO) {
        Comment comment = new Comment();

        comment.setCommentId(commentDTO.getPk());
        comment.setCommentText(commentDTO.getDescription());
        comment.setCreatedAt(LocalDateTime.ofInstant(
                Instant.ofEpochMilli(commentDTO.getCreatedAt()), TimeZone.getDefault().toZoneId()));

        Ad ad = adService.getADById(commentDTO.getAuthor());
        comment.setAd(ad);

        User author = userService.getUserById(commentDTO.getAuthor());
        comment.setAuthor(author);

        return comment;
    }

    public static CommentsDTO toCommentsDTO(List<Comment> comments) {
        CommentsDTO commentsDTO = new CommentsDTO();
        commentsDTO.setCount(comments.size());
        commentsDTO.setResults(comments.stream().map(CommentMapper::toCommentDTO).collect(Collectors.toList()));

        return commentsDTO;
    }
}
