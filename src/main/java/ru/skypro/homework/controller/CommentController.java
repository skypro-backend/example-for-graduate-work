package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

@Slf4j
@RequestMapping("/ads/{id}/comments")
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Комментарии", description = "Управление комментариями")
public class CommentController {

    private final CommentService commentService;
    private final AdService adService;

    @Operation(summary = "Получение комментариев объявления", tags = {"Комментарии"})
    @GetMapping("")
    public ResponseEntity<?> getComments(@RequestParam("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                return ResponseEntity.ok(commentService.getCommentsOfAd(id));
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Добавление комментария к объявлению", tags = {"Комментарии"})
    @PostMapping("")
    public ResponseEntity<?> addComment(@RequestParam("id") long id, @RequestBody CreateOrUpdateComment newComment,
                                        @AuthenticationPrincipal UserDetails currentUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(id)) {
                commentService.createComment(id, newComment, currentUser);
                return ResponseEntity.ok("комментарий добавлен");
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Удаление комментария", tags = {"Комментарии"})
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestParam("adId") long adId, @RequestParam("commentId") long commentId,
                                           @AuthenticationPrincipal UserDetails currentUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(adId) && commentService.commentExist(commentId)){
                if (commentService.commentPostedByUser(commentId,currentUser)) {
                    commentService.deleteComment(commentId);
                    return ResponseEntity.ok("комментарий удалён");
                } else
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("пользователь не является владельцем объявления");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }

    @Operation(summary = "Обновление комментария", tags = {"Комментарии"})
    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestParam("adId") long adId, @RequestParam("commentId") long commentId,
                                           @RequestBody CreateOrUpdateComment comment,
                                           @AuthenticationPrincipal UserDetails currentUser) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            if (adService.existAd(adId) && commentService.commentExist(commentId)){
                if (commentService.commentPostedByUser(commentId,currentUser)) {
                    commentService.updateComment(commentId,comment);
                    return ResponseEntity.ok("комментарий обновлен");
                } else
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("пользователь не является владельцем объявления");
            }else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("такого объявления не найдено");
        } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("залогинься");
    }
}
