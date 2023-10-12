package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDtos.CommentDto;
import ru.skypro.homework.dto.CommentsDtos.CommentTextDto;
import ru.skypro.homework.dto.CommentsDtos.CommentsListDto;

import java.util.ArrayList;


@RequestMapping(value = "/ads")
@RestController
@RequiredArgsConstructor
public class CommentsController {

    //private final CommentsService commentsService;

    @GetMapping("/{id}/comments")

    public ResponseEntity<CommentsListDto> GetComments(@PathVariable("id") int adsId) {
        var commentDto = new CommentDto();
        commentDto.setAuthor(1);
        commentDto.setAuthorImage("Stone");
        commentDto.setAuthorFirstName("Nik");
        commentDto.setCreatedAt(2);
        commentDto.setPk(2);
        commentDto.setText("Amazing");
        var commentsList = new ArrayList<CommentDto>();
        commentsList.add(commentDto);
        var commentsListDto = new CommentsListDto();//создаем объект класса коментслистдто и присваеваем объект переменной соентслист дто.
        commentsListDto.setCount(1);
        commentsListDto.setResults(commentsList);
        return ResponseEntity.ok(commentsListDto);//возврашаем список комантариев со счетчиком
    }

    @PostMapping("/{id}/comments")

    public ResponseEntity<CommentDto> AddComment(@PathVariable("id") int adsId, @RequestBody CommentTextDto commentTextDto) {
        var commentDto = new CommentDto();
        commentDto.setAuthor(1);
        commentDto.setAuthorImage("Stone");
        commentDto.setAuthorFirstName("Nik");
        commentDto.setCreatedAt(2);
        commentDto.setPk(2);
        commentDto.setText(commentTextDto.getText());
        return ResponseEntity.ok(commentDto);
    }

    @DeleteMapping("{adId}/comments/{commentId}")

    public ResponseEntity<?> DeleteComment(@PathVariable("adId") int adsId, @PathVariable("commentsId") int commentId) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("{adId}/comments/{commentId}")

    public ResponseEntity<CommentDto> Update(@PathVariable("adID") int adsId, @PathVariable("commentsId") int commentId, @RequestBody CommentTextDto commentTextDto) {
        var commentDto = new CommentDto();
        commentDto.setAuthor(1);
        commentDto.setAuthorImage("Stone");
        commentDto.setAuthorFirstName("Nik");
        commentDto.setCreatedAt(2);
        commentDto.setPk(commentId);
        commentDto.setText(commentTextDto.getText());
        return ResponseEntity.ok(commentDto);
    }


}



