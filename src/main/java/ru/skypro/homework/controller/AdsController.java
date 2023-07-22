package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsService adsService;

    private  final CommentService commentService;

    @GetMapping("/")
    public ResponseEntity<ResponseWrapperAds> getAllAds() {

        return ResponseEntity.ok(adsService.getAllAds());
    }

    //!Доработать метод
    @PostMapping("/")
    public ResponseEntity<Void> addAd(@RequestBody CreateAds ads) {
        adsService.createAds(ads);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable int id){
        return ResponseEntity.ok(commentService.getUserComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable int id, @RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.addComment(id, commentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable int id){
        return ResponseEntity.ok(adsService.getFullAdById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAd(@PathVariable int id){
        adsService.deleteAd(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAds(@PathVariable int id , @RequestBody CreateAds ads){
       return ResponseEntity.ok(adsService.updateAd(id, ads));
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int adId , @PathVariable int commentId){
        commentService.deleteComment(adId,commentId);
       return ResponseEntity.ok().build();
    }

    @PostMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable int adId , @PathVariable int commentId, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(adId,commentId,commentDTO));
    }
    //!Доработать метод
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(){
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    //!Доработать метод
    @PostMapping("/{id}/image")
    public ResponseEntity<Void> updateImage(@PathVariable int id){
        return ResponseEntity.ok().build();
    }





}