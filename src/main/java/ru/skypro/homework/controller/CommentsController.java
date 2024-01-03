package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.model.utils.CommentDtoWithHttpStatus;
import ru.skypro.homework.service.CommentsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentsController {
    final private CommentsService commentsService;
    /**
     *GET /ads/{id}/comments <h2>Получение комментариев объявления</h2>
     * @param id Advertisement identifier
     * @return List of comments related to advertisement with provided identifier
     */

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<CommentsDto> getComments(@Parameter(name = "id", description = "Advertisement identifier")
                                                   @PathVariable(name = "id") int id) {
        CommentsDto commentsDto = commentsService.findCommentsRelatedToAd(id);
        return new ResponseEntity<CommentsDto>(commentsDto, HttpStatus.OK);
    }


    /**
     * POST /ads/{id}/comments <h2>Добавление комментария к объявлению</h2>
     * @param id Advertisement identifier
     * @param comment Comment body
     * @return responses: '200': OK, content: application/json: schema: $ref: '#/components/schemas/Comment'
     *         <br>'401': Unauthorized
     *         <br>'404': Not found
     */
    @PostMapping("/ads/{id}/comments")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<CommentDto> addComment(@PathVariable(name = "id") int id, String username,
                                                 @RequestBody CreateOrUpdateCommentDto comment){
        CommentDtoWithHttpStatus commentDtoWithHttpStatus = commentsService.addComment(id, username, comment);
        return new ResponseEntity<CommentDto>(commentDtoWithHttpStatus.getCommentDto(),
                commentDtoWithHttpStatus.getHttpStatus());
    }

    /**
     * DELETE /ads/{adId}/comments/{commentId} <h2>Удаление комментария</h2>
     * @param adId Advertisement identifier
     * @param commentId Comment identifier
     * @return '200':
     *           description: OK <br>
     *         '401':
     *           description: Unauthorized <br>
     *         '403':
     *           description: Forbidden <br>
     *         '404':
     *           description: Not found <br>
     */
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("#userName == authentication.principal.username")
    public ResponseEntity<HttpStatus> deleteComment(
            @PathVariable(name = "adId") long adId,
            @PathVariable(name = "commentId") long commentId, String userName) {
        return commentsService.deleteComment(adId, commentId, userName);
    }


    /**
     * PATCH /ads/{adId}/comments/{commentId} <h2>Обновление комментария</h2>
     * @param adId Advertisement identifier
     * @param commentId Comment identifier
     * @param comment New Comment body
     * @return '200':
     *           description: OK
     *           content:
     *             application/json:
     *               schema:
     *                 $ref: '#/components/schemas/Comment'
     *         <br>'401':
     *           description: Unauthorized
     *         <br>'403':
     *           description: Forbidden
     *         <br>'404':
     *           description: Not found
     */
    @PreAuthorize("#userName == authentication.principal.username")
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "adId") long adId,
            @PathVariable(name = "commentId") long commentId,
            String userName,
            @RequestBody CreateOrUpdateCommentDto comment) {

        CommentDtoWithHttpStatus commentDtoWithHttpStatus =
                commentsService.updateComment(adId, commentId, userName, comment);

        return new ResponseEntity<>(
                commentDtoWithHttpStatus.getCommentDto(),
                commentDtoWithHttpStatus.getHttpStatus());
    }
}

