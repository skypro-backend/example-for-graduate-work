package ru.skypro.homework.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @PostMapping
    public ResponseEntity<Ad> addAd(@RequestPart("properties") CreateOrUpdateAd properties, @RequestPart("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adsService.addAd(properties, image));

    }
    @GetMapping(value = "/{Id}")
    public ResponseEntity<ExtendedAd> getAds (@PathVariable Integer id) {
        return new ResponseEntity<>(adsService.getAds(id), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<Void> removeAd(@PathVariable Integer id) {
        adsService.removeAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping(value = "/{Id}")
    public ResponseEntity<CreateOrUpdateAd> updateAds(@PathVariable Integer id,
                                                      @RequestBody CreateOrUpdateAd updateAd)
    {
        return new ResponseEntity<>(adsService.updateAd(id,updateAd), HttpStatus.OK);
    }
    @GetMapping(value = "/me")
    public ResponseEntity<Ads> getAdsMe() {

        return new ResponseEntity<>(adsService.getAdsMe(), HttpStatus.OK);
    }

    @PatchMapping(value = "/{Id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile image) throws IOException {
        adsService.updateImage(id, image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
