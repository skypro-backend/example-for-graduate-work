package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentsController {
    /**
     *GET /ads/{id}/comments <h2>Получение комментариев объявления</h2>
     * @param id Comment identifier
     * @return List of comments
     */

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<CommentsDto> getComments(@PathVariable(name = "id") long id){
        return new ResponseEntity<CommentsDto>(new CommentsDto(), HttpStatus.OK);
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
    public ResponseEntity<CommentDto> addComment(@PathVariable(name = "id") long id,
                                                 @RequestBody CreateOrUpdateCommentDto comment){
        return new ResponseEntity<CommentDto>(new CommentDto(), HttpStatus.OK);

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
    public ResponseEntity<HttpStatus> deleteComment(
            @PathVariable(name = "adId") long adId,
            @PathVariable(name = "commentId") long commentId){

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     *  '200':
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Comment'
        '401':
          description: Unauthorized
        '403':
          description: Forbidden
        '404':
          description: Not found*/

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
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(name = "adId") long adId,
            @PathVariable(name = "commentId") long commentId,
            @RequestBody CreateOrUpdateCommentDto comment) {
        return new ResponseEntity<>(new CommentDto(), HttpStatus.OK);
    }
}

