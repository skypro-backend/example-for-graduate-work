package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication) {
        String username = authentication.getName();
        var body = advertService.getAdvert(username);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAdById(@PathVariable int id) {
        var body = advertService.getAdvertById(id);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getCommentsForAd(@PathVariable int id) {
        var body = commentService.getAllCommentsAdvert(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<AdDto> addAd(Authentication authentication, @RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") MultipartFile image) {
        String username = authentication.getName();
        var body = advertService.createAdvert(username, createOrUpdateAdDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto) {
        var body = advertService.updateAdvert(id, createOrUpdateAdDto);
        return ResponseEntity.ok((AdDto) body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable int id) {
        advertService.deleteAdvert(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<String> updateAdImage(@PathVariable int id, @RequestPart MultipartFile image, Authentication authentication) {
        String username = authentication.getName();
        var body = advertService.updateAdImage(username, id, image);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds() {
        var body = advertService.getAllAdverts();
        return ResponseEntity.ok(body);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addCommentToAd(@PathVariable int id, Authentication authentication, @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        String username = authentication.getName();
        var body = commentService.createComment(username, id, createOrUpdateCommentDto);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int id, @PathVariable int commentId) {
        commentService.deleteComment(id, commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int id, @PathVariable int commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto, Authentication authentication) {
        String username = authentication.getName();
        var body = commentService.updateComment(username, id, commentId, createOrUpdateCommentDto);
        return ResponseEntity.ok(body);
    }
}
