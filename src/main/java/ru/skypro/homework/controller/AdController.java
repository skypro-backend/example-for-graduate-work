package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdvertService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
@CrossOrigin("http://localhost:3000")
public class AdController {
    private final AdvertService advertService;
    private final CommentService commentService;
    private final ImageService imageService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AdsDto> getAdsMe() {
        var body = advertService.getAdvert();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable int id) {
        var body = advertService.getAdvertById(id);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}/comments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentsDto> getCommentsForAd(@PathVariable int id) {
        var body = commentService.getAllCommentsAdvert(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") MultipartFile image) {
        var body = advertService.createAdvert(createOrUpdateAdDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdDto> updateAd(@PathVariable int id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        var body = advertService.updateAdvert(id, createOrUpdateAdDto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        advertService.deleteAdvert(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/image")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateAdImage(@PathVariable int id,
                                                @RequestPart MultipartFile image) {
        var body = advertService.update(id, image);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<AdsDto> getAllAds() {
        var body = advertService.getAllAdverts();
        return ResponseEntity.ok(body);
    }

    @PostMapping("/{id}/comments")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable int id,
                                                     @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        var body = commentService.createComment(id, createOrUpdateCommentDto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteComment(@PathVariable int id, @PathVariable int commentId) {
        commentService.deleteComment(id, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/comments/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int id,
                                                    @PathVariable int commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        var body = commentService.updateComment(id, commentId, createOrUpdateCommentDto);
        return ResponseEntity.ok(body);
    }
}
