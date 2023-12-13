package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    /**
     * Getting all advertisements
     * using {@link AdService#findAllAds()}
     * @return Ads
     */
    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adService.findAllAds());
    }

    /**
     * Adding advertisement using
     * {@link AdService#createAd(CreateOrUpdateAd, Authentication, MultipartFile)}
     * <br>
     * @param properties
     * @param image
     * @param authentication
     * @return AdDTO
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/ads", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<AdDTO> postAd(@RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image,
                                    Authentication authentication) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adService.createAd(properties, authentication, image));
    }

    /**
     * Getting information about ad by id
     * using {@link AdService#getAd(int)}
     * @param id
     * @return ExtendedAd
     */
    @GetMapping("/ads/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adService.getAd(id));
    }

    /**
     * Removing ad with ADMIN authority or with authority of user with username matching author's by id
     * using {@link AdService#deleteAd(int)}
     * @param id
     * @return
     */
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update ad information with ADMIN authority or with authority of user with username matching author's by id
     * <br>
     * {@link AdService#updateAd(int, CreateOrUpdateAd)},
     * @param id
     * @param createOrUpdateAd
     * @return AdDTO
     */
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{id}")
    public ResponseEntity<AdDTO> editAd(@PathVariable int id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adService.updateAd(id, createOrUpdateAd));
    }

    /**
     * Getting advertisements from an authorized user
     * using {@link AdService#findAllUserAds(Authentication)}
     * @param authentication
     * @return Ads
     */
    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAllMyAds(Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adService.findAllUserAds(authentication));
    }

    /**
     * Update ad image by its pk
     * <br>
     * Using {@link AdService#editAdImage(int, MultipartFile)}
     * @param id
     * @param image
     * @return String
     * @throws IOException
     */
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<String> updateAdImage(@PathVariable int id,
                                                @RequestPart("image") MultipartFile image) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(adService.editAdImage(id, image));
    }

}

