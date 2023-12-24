package ru.skypro.homework.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AdsServiceImpl;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
//@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final AdsServiceImpl adsService;

    public AdsController(AdsServiceImpl adsService) {
        this.adsService = adsService;
    }

    @GetMapping
    public ResponseEntity<Ads> getAllAds() {
        return new ResponseEntity<>(adsService.getAllAds(), HttpStatus.OK);
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties
            ,@RequestPart("image") MultipartFile image
            ,@AuthenticationPrincipal CustomUserDetails userDetails) throws IOException{
        return new ResponseEntity<>(adsService.addAd(properties,image, userDetails),HttpStatus.CREATED);

    }
    @GetMapping(value = "/{Id}")
    public ResponseEntity<ExtendedAd> getAds (@PathVariable Integer id) {
        return new ResponseEntity<>(adsService.getAds(id), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id,@AuthenticationPrincipal CustomUserDetails userDetails) {
        adsService.removeAd(id, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{Id}")
    public ResponseEntity<CreateOrUpdateAd> updateAds(@PathVariable Integer id,
                                                      @RequestBody CreateOrUpdateAd updateAd,
                                                      @AuthenticationPrincipal CustomUserDetails userDetails)
    {
        return new ResponseEntity<>(adsService.updateAd(id,updateAd,userDetails), HttpStatus.OK);
    }
    @GetMapping(value = "/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return new ResponseEntity<>(adsService.getAdsMe(userDetails), HttpStatus.OK);
    }

    @PatchMapping(value = "/{Id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile image,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        adsService.updateImage(id, image, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
