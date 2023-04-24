package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.ResponseWrapperCommentDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.CommentsService;
import ru.skypro.homework.service.UsersService;

import javax.annotation.security.RolesAllowed;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@AllArgsConstructor
@Tag(name = "Комментарии")
public class CommentsController {
    private final CommentsService commentsService;
    private final UsersService usersService;

    // Получить комментарии объявления
    @Operation(summary = "Получить комментарии объявления", tags = "Комментарии")
    @GetMapping("/{id}/comments")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<ResponseWrapperCommentDTO> getAdComments(@PathVariable(name = "id") Long adId) {
        ResponseWrapperCommentDTO dto = commentsService.getAdComments(adId);
        return ResponseEntity.ok(dto);
    }

    // Добавить комментарий к объявлению
    @Operation(
            summary = "Добавить комментарий к объявлению", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PostMapping("/{id}/comments")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<CommentDTO> addComment(@PathVariable(name = "id") Long adId,
                                          @RequestBody CommentDTO commentDTO,
                                          Authentication authentication) {
        UserDTO userDTO = usersService.getAuthorisedUser(authentication.getName());
        return ResponseEntity.ok(commentsService.addComment(adId, commentDTO, userDTO.getId()));
    }

    // Удалить комментарий
    @Operation(
            summary = "Удалить комментарий", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @DeleteMapping("/{adId}/comments/{commentId}")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<?> deleteComment(@PathVariable(name = "adId") Long adId,
                                    @PathVariable(name = "commentId") Long commentId) {
        commentsService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    // Обновить комментарий
    @Operation(
            summary = "Обновить комментарий", tags = "Комментарии",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CommentDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
            }
    )
    @PatchMapping("/{adId}/comments/{commentId}")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<CommentDTO> updateComment(@PathVariable(name = "adId") Long adId,
                                             @PathVariable(name = "commentId") Long commentId,
                                             @RequestBody CommentDTO commentDTO,
                                             Authentication authentication) {
        UserDTO userDTO = usersService.getAuthorisedUser(authentication.getName());
        return ResponseEntity.ok(commentsService.updateComment(adId, commentId, commentDTO, userDTO.getId()));
    }
}
