package ru.skypro.flea.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.flea.controller.CommentApi;
import ru.skypro.flea.dto.CommentDto;
import ru.skypro.flea.dto.CommentsDto;
import ru.skypro.flea.dto.CreateOrUpdateCommentDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Comments")
public class CommentApiController implements CommentApi {

    @Override
    public ResponseEntity<CommentsDto> getComments(int id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommentDto> addComment(int id,
                                                 CreateOrUpdateCommentDto comment) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteComment(int adId,
                                              int commentId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CommentDto> updateComment(int adId,
                                                    int commentId,
                                                    CreateOrUpdateCommentDto comment) {
        return ResponseEntity.ok().build();
    }

}
