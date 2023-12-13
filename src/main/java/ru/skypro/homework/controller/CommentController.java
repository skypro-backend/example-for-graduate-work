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
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Getting ad comments by adId
     * <br>
     * Using {@link CommentService#findAdComments(int)}
     * @param adId
     * @return Comments
     */
    @GetMapping("/ads/{adId}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int adId) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(commentService.findAdComments(adId));
    }

    /**
     * Adding a comment to ad by adId
     * <br>
     * Using {@link CommentService#createComment(int, CreateOrUpdateComment, Authentication)} 
     * @param adId
     * @param createOrUpdateComment
     * @param authentication
     * @return CommentDTO
     */
    @PostMapping("/ads/{adId}/comments")
    public ResponseEntity<CommentDTO> postComment(@PathVariable int adId,
                                                  @RequestBody CreateOrUpdateComment createOrUpdateComment,
                                                  Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(commentService.createComment(adId,
                        createOrUpdateComment,
                        authentication));
    }

    /**
     * Deleting a comment by adId & commentId
     * <br>
     * Using {@link CommentService#deleteComment(int, int)}
     * @param adId
     * @param commentId
     * @return
     */
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or @commentRepository.findByPk(#commentId).getAuthor().getUsername() == authentication.name")
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId,
                                              @PathVariable int commentId) {
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    /**
     * Comment update by adId & commentId
     * <br>
     * Using {@link CommentService#editComment(int, int, CreateOrUpdateComment)}
     * @param adId
     * @param commentId
     * @param createOrUpdateComment
     * @return CommentDTO
     */
    @PreAuthorize("hasAuthority('ADMIN') or @commentRepository.findByPk(#commentId).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId,
                                                    @PathVariable int commentId,
                                                    @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(commentService.editComment(adId, commentId, createOrUpdateComment));
    }

}
