package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ads")
public class CommentsController {
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария в объявлении")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<Void> removeComment(@PathVariable("adId") Long adId, @PathVariable("commentId") Long commentId) {
        // Реализация удаления комментария в объявлений
        return ResponseEntity.noContent().build();
    }
}
