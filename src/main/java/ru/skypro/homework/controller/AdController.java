package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("ads")
@RequiredArgsConstructor
public class AdController {
    private final AdService adService;
    private final CommentService commentService;
    private final AdMapper adMapper;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok(adMapper.toAds(adService.getAllAds()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable Long id) {
        return ResponseEntity.ok(adMapper.toExtendedAd(adService.getAdById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> createAd(
            @RequestPart("image") MultipartFile image,
            @RequestPart("properties") CreateOrUpdateAd dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adMapper.toAdDTO(adService.createAd(dto, image)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(
            @PathVariable Long id,
            @RequestBody CreateOrUpdateAd dto
    ) {
        return ResponseEntity.ok(adMapper.toAdDTO(adService.updateAd(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getMeAds() {
        return ResponseEntity.ok(adMapper.toAds(adService.getMyAds()));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> updateAdImage(@PathVariable("id") long id,
                                               @RequestPart(value = "image") MultipartFile image) {
        return ResponseEntity.ok(adMapper.toAdDTO(adService.updateAdImage(id, image)));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getAdComments(@PathVariable("id") long id) {
        return ResponseEntity.ok(commentMapper.toComments(commentService.getComments(id)));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> createAdComment(
            @PathVariable("id") long id,
            @RequestBody CreateOrUpdateComment dto
    ) {
        return ResponseEntity.ok(commentMapper.toCommentDTO(commentService.addComment(id, dto)));
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<CommentDTO> deleteAdComment(
            @PathVariable("id") long id,
            @PathVariable("commentId") long commentId
    ) {
        commentService.deleteComment(id, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateAdComment(
            @PathVariable("id") long id,
            @PathVariable("commentId") long commentId,
            @RequestBody CreateOrUpdateComment dto
    ) {
        return ResponseEntity.ok(commentMapper.toCommentDTO(commentService.updateComment(id, commentId, dto)));
    }
}
