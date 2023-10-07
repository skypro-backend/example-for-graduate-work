package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import javax.xml.stream.events.Comment;

/**
 * Контроллер для работы с комментариями
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")

public class CommentController {
private CommentServiceImpl commentService;

    @Operation(
            summary = "получение комментариев объявления",
            tags= "Комментарии"
    )

@GetMapping("/{id}/comments")

    public CommentDto getComment(@PathVariable(value = "id") int id){

        return getComment(id);
    }

    @Operation(

            summary = "добавление комментариев к объявлению",
            tags= "Комментарии"
    )
    @PostMapping("/{id}/comments")
    public CommentDto addComment(@PathVariable(value = "id") int CommentId){

        return new CommentDto();
    }

    @Operation(

            summary = "удаление комментария",
            tags= "Комментарии"
    )
    @DeleteMapping("/{id}/comments/{commentId}")
    public CommentDto deleteCommend(@PathVariable(value ="id") int CommentId){
        return new CommentDto();
    }

    @Operation(

            summary = "обновление комментария",
            tags= "Комментарии"
    )
    @PatchMapping("/{id}/comments/{commentId}")
    public  CommentDto updateComment(@PathVariable(value = "id") int CommentId){
        return new CommentDto();
    }















}
