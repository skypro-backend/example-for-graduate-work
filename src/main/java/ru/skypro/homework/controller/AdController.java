package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/ads")
public class AdController {

    private final AdService adService;
    private final CommentService commentService;
    private final ImageService imageService;

    @GetMapping()
    public ResponseEntity<AdsDTO> getAllAds() {
        AdsDTO adsDTO = adService.getAllAds();
        return ResponseEntity.ok(adsDTO);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDTO> addAd(@RequestPart CreateOrUpdateAdDTO createOrUpdateAdDTO,
                                       @RequestPart MultipartFile image) {
        AdDTO createdAd = adService.createAd(createOrUpdateAdDTO, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAd);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDTO> getAd(@PathVariable Integer id) {
        ExtendedAdDTO fullAd = adService.getFullAd(id);
        return ResponseEntity.ok(fullAd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletedAd(@PathVariable Integer id) {
        Boolean removed = adService.removeAd(id);
        return ResponseEntity.ok(removed);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDTO> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        AdDTO updatedAd = adService.updateAd(id, createOrUpdateAdDTO);
        return ResponseEntity.ok(updatedAd);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDTO> getAdForMe() {
        AdsDTO userAd = adService.getAllUserAd();
        return ResponseEntity.ok(userAd);
    }


    @PatchMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable Integer id,
                                            @RequestPart MultipartFile image) {
        adService.updateImageAd(id, image);
        return ResponseEntity.ok().build();
    }

    //Comments

    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDTO> getComments(@PathVariable("id") Integer adId) {
        CommentsDTO commentsDTO = commentService.getComments(adId);
        return ResponseEntity.ok(commentsDTO);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable("id") Integer adId,
                                                 @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        CommentDTO addedComment = commentService.addComment(adId, createOrUpdateCommentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedComment);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComent(@PathVariable Integer adId,
                                             @PathVariable Integer commentId) {
        commentService.deleteComment(adId,commentId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Integer adID,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO createOrUpdateCommentDTO) {
        CommentDTO updatedComment = commentService.updateComment(adID, commentId, createOrUpdateCommentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) {
        byte[]imageData = imageService.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
    }

}

