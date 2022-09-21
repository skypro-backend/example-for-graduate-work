package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.dto.AdsCommentDto;
import ru.skypro.homework.models.dto.AdsDto;
import ru.skypro.homework.models.dto.CreateAdsDto;
import ru.skypro.homework.models.dto.FullAdsDto;
import ru.skypro.homework.models.dto.ResponseWrapper;
import ru.skypro.homework.models.entity.Images;
import ru.skypro.homework.service.AdsCommentsService;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    private final AdsService adsService;
    private final AdsCommentsService adsCommentsService;
    private final ImageService imageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrapper<AdsDto> getALLAds() {
        List<AdsDto> list = adsService.getALLAds();

        return new ResponseWrapper<>(list);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public AdsDto addAds(@Valid @RequestPart("properties") @Parameter(schema = @Schema(type = "string", format = "binary")) CreateAdsDto ads,
                         @RequestPart("image") MultipartFile file) {
        Images images = imageService.addImage(file);

        return adsService.addAds(ads, images);
    }

    @GetMapping("me")
    public ResponseWrapper<AdsDto> getAdsMe(@RequestParam(required = false) Boolean authenticated,
                                            @RequestParam(required = false) String authority,
                                            @RequestParam(required = false) Object credentials,
                                            @RequestParam(required = false) Object details,
                                            @RequestParam(required = false) Object principal) {
        List<AdsDto> list = adsService.getAdsMe(authenticated, authority, credentials, details, principal);

        return new ResponseWrapper<>(list);
    }

    @GetMapping("{ad_pk}/comments")
    public ResponseWrapper<AdsCommentDto> getAdsComments(@PathVariable String ad_pk) {
        List<AdsCommentDto> list = adsCommentsService.getAdsComments(ad_pk);

        return new ResponseWrapper<>(list);
    }

    @PostMapping("{ad_pk}/comments")
    public AdsCommentDto addAdsComments(@PathVariable String ad_pk, @RequestBody AdsCommentDto comment) {
        return adsCommentsService.addAdsComments(ad_pk, comment);
    }

    @DeleteMapping("{ad_pk}/comments/{id}")
    public void deleteAdsComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        adsCommentsService.deleteAdsComment(ad_pk, id);
    }

    @GetMapping("{ad_pk}/comments/{id}")
    public AdsCommentDto getAdsComments(@PathVariable String ad_pk, @PathVariable Integer id) {
        return adsCommentsService.getAdsComment(ad_pk, id);
    }

    @PatchMapping("{ad_pk}/comments/{id}")
    public AdsCommentDto updateAdsComments(@PathVariable String ad_pk, @PathVariable Integer id, @RequestBody AdsCommentDto comment) {
        return adsCommentsService.updateAdsComment(ad_pk, id, comment);
    }

    @DeleteMapping("{id}")
    public void removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
    }

    @GetMapping("{id}")
    public FullAdsDto getAds(@PathVariable Integer id) {
        return adsService.getFullAds(id);
    }

    @PatchMapping("{id}")
    public AdsDto updateAds(@PathVariable Integer id, @Valid @RequestBody CreateAdsDto ads) {
        return adsService.updateAds(id, ads);
    }
}
