package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.models.dto.*;
import ru.skypro.homework.service.AdsCommentsService;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    private final AdsService adsService;
    private final AdsCommentsService adsCommentsService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<AdsDto>> getALLAds() {
        List<AdsDto> list = adsService.getALLAds();
        // if ... exception
        return ResponseEntity.ok(new ResponseWrapper(list));
    }

    @PostMapping
    public ResponseEntity<AdsDto> addAds(@RequestBody CreateAdsDto ads) {
        AdsDto adsDto = adsService.addAds(ads);
        // if ... exception
        return ResponseEntity.ok(adsDto);
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapper<AdsDto>> getAdsMe(@RequestParam(required = false) Boolean authenticated,
                                                            @RequestParam(required = false) String authority,
                                                            @RequestParam(required = false) Object credentials,
                                                            @RequestParam(required = false) Object details,
                                                            @RequestParam(required = false) Object principal) {
        List<AdsDto> list = adsService.getAdsMe(authenticated, authority, credentials, details, principal);
        // if ... exception
        return ResponseEntity.ok(new ResponseWrapper(list));
    }

    @GetMapping("{ad_pk}/comment")
    public ResponseEntity<ResponseWrapper<AdsCommentDto>> getAdsComments(@PathVariable  String ad_pk) {
        List<AdsCommentDto> list = adsCommentsService.getAdsComments(ad_pk);
        // if ... exception
        return ResponseEntity.ok(new ResponseWrapper(list));
    }

    @PostMapping("{ad_pk}/comment")
    public ResponseEntity<AdsCommentDto> addAdsComments(@PathVariable  String ad_pk, @RequestBody AdsCommentDto comment) {
        AdsCommentDto result = adsCommentsService.addAdsComments(ad_pk, comment);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{ad_pk}/comment/{id}")
    public void deleteAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id) {
        adsCommentsService.deleteAdsComments(ad_pk, id);
    }

    @GetMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> getAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id) {
        AdsCommentDto result = adsCommentsService.getAdsComments(ad_pk, id);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @PatchMapping("{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComments(@PathVariable  String ad_pk, @PathVariable Integer id, @RequestBody AdsCommentDto comment) {
        AdsCommentDto result = adsCommentsService.updateAdsComments(ad_pk, id, comment);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{id}")
    public void removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable Integer id) {
        FullAdsDto result = adsService.getAds(id);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id, @RequestBody AdsDto ads) {
        AdsDto result = adsService.updateAds(id, ads);
        // if ... exception
        return ResponseEntity.ok(result);
    }

}
