package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {
    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        ResponseWrapperAds allAds = adsService.getAllAds();
        if (allAds.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allAds);
    }

    @PostMapping
    public ResponseEntity<AdsDto> addAds(@RequestBody CreateAds createAds) {
        if (createAds == null) {
            return ResponseEntity.notFound().build();
        }
        AdsDto adsDto = adsService.addAds(createAds);
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(adsDto);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestParam(value = "authenticated", required = false) Boolean authenticated,
                                                       @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority,
                                                       @RequestParam(value = "credentials", required = false) Object credentials,
                                                       @RequestParam(value = "details", required = false) Object details,
                                                       @RequestParam(value = "principal", required = false) Object principal) {
        ResponseWrapperAds Ads = adsService.getAdsMe(authenticated, authorities0Authority, credentials, details, principal);
        if (Ads.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Ads);
    }

    @GetMapping("/{ad_pk}/comment")
    public ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@PathVariable("ad_pk") String adPk) {
        int pk = Integer.parseInt(adPk);
        if (pk < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ResponseWrapperAdsComment adsComment = adsService.getAdsComments(pk);
        if (adsComment.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adsComment);
    }

    @PostMapping("/{ad_pk}/comment")
    public ResponseEntity<AdsCommentDto> addAdsComment(@PathVariable("ad_pk") String adPk, @RequestBody AdsCommentDto adsCommentDto) {
        int pk = Integer.parseInt(adPk);
        if (pk < 0 || adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsComment = adsService.addAdsComment(pk, adsCommentDto);
        if (adsComment == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(adsComment);
    }

    @DeleteMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity deleteAdsComment(@PathVariable("ad_pk") String adPk,
                                           @PathVariable int id) {
        int pk = Integer.parseInt(adPk);
        if (pk < 0 || id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsCommentDto = adsService.deleteAdsComment(pk, id);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> getAdsComment(@PathVariable("ad_pk") String adPk,
                                                       @PathVariable int id) {
        int pk = Integer.parseInt(adPk);
        if (pk < 0 || id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsCommentDto = adsService.getAdsComment(pk, id);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsCommentDto);
    }


    @PatchMapping("/{ad_pk}/comment/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable("ad_pk") String adPk,
                                                          @PathVariable int id,
                                                          @RequestBody AdsCommentDto adsCommentDto) {
        int pk = Integer.parseInt(adPk);
        if (pk < 0 || id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsCommentDto adsComment = adsService.updateAdsComment(pk, id, adsCommentDto);
        if (adsCommentDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdsDto> removeAds(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsDto adsDto = adsService.removeAds(id);
        if (adsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        FullAds fullAds = adsService.getAds(id);
        if (fullAds == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(fullAds);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int id, @RequestBody AdsDto adsDto) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        AdsDto ads = adsService.updateAds(id, adsDto);
        if (ads == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(adsDto);
    }
}