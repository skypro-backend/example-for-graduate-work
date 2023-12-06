package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final AdRepository adRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @GetMapping("/ads/{adId}/comments")
    public Comments getComments(@PathVariable Integer adId) {
        Ad ad = adRepository.findByPk(adId);
        Comments commentsDTO = commentMapper.mapToListOfDTO(ad);
        return commentsDTO;
    }

    @PostMapping("/ads/{adId}/comments")
    public ResponseEntity<CommentDTO> postComment(@PathVariable Integer adId,
                                                  @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                  Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Ad ad = adRepository.findByPk(adId);
        Comment comment = commentMapper.createFromCreateOrUpdate(createOrUpdateComment, user, ad);
        CommentDTO commentDTO = commentMapper.mapToDTO(comment);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(commentDTO);
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or @commentRepository.findByPk(#commentId).getAuthor().getUsername() == authentication.name")
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer adId,
                                              @PathVariable Integer commentId) {
        commentRepository.deleteByAdPkAndPk(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN') or @commentRepository.findByPk(#commentId).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        Ad ad = adRepository.findByPk(adId);
        Comment comment = commentRepository.findByPk(commentId);
        comment = commentMapper.updateFromCreateOrUpdate(comment, createOrUpdateComment);
        CommentDTO commentDTO = commentMapper.mapToDTO(comment);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(commentDTO);
    }

}
