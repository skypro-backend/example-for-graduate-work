package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.adsDTO.AdsAllCommentsDTO;
import ru.skypro.homework.dto.adsDTO.AdsCommentDTO;
import ru.skypro.homework.dto.adsDTO.AdsCreateDTO;

import java.util.List;
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class AdsCommentsController {

    @GetMapping("/all")
    public ResponseEntity<List<AdsAllCommentsDTO>> getAllComments() {
        return ResponseEntity.ok(AdsAllCommentsDTO.getAllCommemts());}
    @PostMapping
    public AdsCommentDTO addComment(@RequestBody AdsCommentDTO adsCommentDTO){
        return adsCommentDTOService.addComments(adsCommentDTO);
    }
    @PostMapping
    public AdsCommentDTO updateComment(@RequestBody AdsCommentDTO adsCommentDTO){
        return adsCommentDTOService.updateComments(adsCommentDTO);
    }
    @DeleteMapping
    public AdsCommentDTO deleteComment(@PathVariable Long createdAt){
        return AdsCommentDTOService.deleteComment(createdAt);

    }
}
