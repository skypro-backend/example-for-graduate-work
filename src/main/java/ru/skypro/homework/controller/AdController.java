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
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;
    private final AdMapper adMapper;
    private final AdRepository adRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    /**
     * Getting all advertisements
     * @return Ads
     */
    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adMapper.mapToListOfDTO(adRepository.findAll()));
    }

    /**
     * Adding advertisement
     * <br>
     * Using for adding ad {@link AdService#createAd(CreateOrUpdateAd, User, Image)}
     * <br>
     * Using {@link Authentication#getName()}
     * {@link UserRepository#findByUsername(String)}
     * {@link ImageService#uploadImage(MultipartFile)}
     * @param properties
     * @param image
     * @param authentication
     * @return AdDTO
     * @throws IOException
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/ads", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ResponseEntity<AdDTO> postAd(@RequestPart(value = "properties", required = true) CreateOrUpdateAd properties,
                                    @RequestPart("image") MultipartFile image,
                                    Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Image imageToDB = imageService.uploadImage(image);
        Ad ad = adService.createAd(properties, user, imageToDB);
        AdDTO adDTO = adMapper.mapToDTO(ad);
        return ResponseEntity.status(HttpStatus.CREATED).body(adDTO);
    }

    /**
     * Getting information about ad by id
     * Using AdRepository method for finding ad by pk(id) {@link AdRepository#findByPk(Integer)}
     * @param id
     * @return ExtendedAd
     */
    @GetMapping("/ads/{id}")
    public ResponseEntity<ExtendedAd> getAdById(@PathVariable int id) {
        HttpHeaders headers = new HttpHeaders();
        Ad ad = adRepository.findByPk(id);
        ExtendedAd extendedAd = adMapper.mapToExtended(ad);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(extendedAd);
    }

    /**
     * Removing ad with ADMIN authority or with authority of user with username matching author's by id
     * using {@link CommentRepository#deleteAllByAdPk(Integer)} {@link AdRepository#deleteByPk(int)}
     * @param id
     * @return
     */
    @Transactional
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Integer id) {
        commentRepository.deleteAllByAdPk(id);
        adRepository.deleteByPk(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update ad information with ADMIN authority or with authority of user with username matching author's by id
     * <br>
     * Using {@link AdRepository#findByPk(Integer)},
     * {@link AdService#updateAd(Ad, CreateOrUpdateAd)},
     * {@link AdMapper#mapToDTO(Ad)}
     * @param id
     * @param createOrUpdateAd
     * @return AdDTO
     */
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{id}")
    public ResponseEntity<AdDTO> editAd(@PathVariable Integer id,
                                        @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        HttpHeaders headers = new HttpHeaders();
        Ad ad = adRepository.findByPk(id);
        ad = adService.updateAd(ad, createOrUpdateAd);
        AdDTO adDTO = adMapper.mapToDTO(ad);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adDTO);
    }

    /**
     * Getting advertisements from an authorized user
     * using {@link Authentication#getName()},
     * {@link UserRepository#findByUsername(String)}
     * @param authentication
     * @return Ads
     */
    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAllMyAds(Authentication authentication) {
        String username = authentication.getName();
        Integer authorId = userRepository.findByUsername(username).getId();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(adMapper.mapToListOfDTO(adRepository.findAllByAuthorId(authorId)));
    }

    /**
     * Update ad image by its pk
     * <br>
     * Using {@link AdRepository#findByPk(Integer)},
     * {@link ImageService#uploadImage(MultipartFile)},
     * {@link Ad#setImage(Image)},
     * {@link AdRepository#save(Object)},
     * @param id
     * @param image
     * @return String
     * @throws IOException
     */
    @PreAuthorize("hasAuthority('ADMIN') or @adRepository.findByPk(#id).getAuthor().getUsername() == authentication.name")
    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<String> updateAdImage(@PathVariable Integer id,
                                                @RequestPart("image") MultipartFile image) throws IOException{
        HttpHeaders headers = new HttpHeaders();
        Ad ad = adRepository.findByPk(id);
        Image imageToDB = imageService.uploadImage(image);
        ad.setImage(imageToDB);
        adRepository.save(ad);
        ImageDTO imageDTO = imageMapper.mapToDTO(imageToDB);
        return ResponseEntity.ok(new ImageDTO().getUrl());
    }

}

