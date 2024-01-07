package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.mapping.AdMapper;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.PictureType;
import ru.skypro.homework.model.utils.AdFound;
import ru.skypro.homework.model.utils.ImageProcessResult;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.AdvertisementsService;

import java.io.IOException;
import java.security.Principal;

/**
 * <h2>Advertisements controller to manage ads</h2>
 */
@Data
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class Advertisements {

    private final AdvertisementsService advertisementsService;

    Logger logger = LoggerFactory.getLogger(Advertisements.class);

    final private ImagesRepository imagesRepository;
    //TODO: remove ImagesRepository from Advertisements controller and training... endpoints

    /**
     * <h2>getAllAds()</h2>
     * GET /ads Получение всех объявлений
     *
     * @return {@link AdsDto}: list of all advertisements found in repository with number of advertisements
     */
    @GetMapping("/ads")
    public ResponseEntity<AdsDto> getAllAds() {
        logger.info("getAllAds invoked");
        return ResponseEntity.ok(advertisementsService.getAll());
    }

    /**
     * <h2>addAd</h2>
     * Добавление объявления
     *
     * @return {@link AdDto}: DTO of added advertisement
     */
    @PostMapping(value = "/ads", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @Operation(description = "Добавление нового объявления")
    public ResponseEntity<AdDto> addAd(@RequestPart(value = "properties") CreateOrUpdateAdDto properties,
                                       @RequestPart(value = "image") MultipartFile image,
                                       Principal principal) throws IOException {
        logger.info("addAd method invoked");
        AdDto newAd = advertisementsService.addNewAd(properties, image, principal);
        return new ResponseEntity<>(newAd, HttpStatus.OK);
    }

    /**
     * <h2>getAds(@PathVariable long id)</h2>
     * GET /ads/{id} Получение информации об объявлении
     * @return DTO of advertisements with given id
     */
    @GetMapping("/ads/{id}")
    public ResponseEntity<AdDto> getAds(@PathVariable long id) {
        AdFound adFound = advertisementsService.getAdById(id);
        return new ResponseEntity<>(AdMapper.INSTANCE.adToDto(adFound.getAd()), adFound.getHttpStatus());
    }

    /**
     * DELETE /ads/{id} <h2>Удаление объявления</h2>
     *
     * @param id advertisement identifier
     * @return response code '204':
     * description: No Content
     * '401':
     * description: Unauthorized
     * '403':
     * description: Forbidden
     * '404':
     * description: Not found
     */
    @DeleteMapping("/ads/{id}")
    @PreAuthorize("#userName == authentication.principal.username")
    public ResponseEntity<HttpStatus> removeAd(@Parameter(name = "id", description = "advertisement identifier")
                                                   @PathVariable int id, Principal principal) {
        logger.info("id: " + id + " | User Name: " + principal.getName());
        AdFound adRemoved = advertisementsService.removeAd((long) id, principal.getName());
        return new ResponseEntity<>(adRemoved.getHttpStatus());
    }

    /** <h2>PATCH /ads/{id} Обновление информации об объявлении</h2>
     * @param id advertisement identifier
     * @return updated advertisement with response code as follows: '200':
     * description: OK<br>
     * content:
     * application/json:
     * schema:
     * $ref: '#/components/schemas/Ad'
     * <br>'403':
     * description: Forbidden
     * <br>'401':
     * description: Unauthorized
     * <br>'404':
     * description: Not found
     */
    @PatchMapping("/ads/{id}")
    @PreAuthorize("#userName == authentication.principal.username")
    ResponseEntity<AdDto> updateAds(@Parameter(name = "id", description = "advertisement identifier")
                                    @PathVariable long id, @RequestBody CreateOrUpdateAdDto updatedAdContent,
                                    String userName) {

        return advertisementsService.updateAd(id, updatedAdContent, userName);

    }


    /**<h2>getAdsMe</h2>
     * GET /ads/me <h3>Получение объявлений авторизованного пользователя</h3>
     *
     * @param principal authentication data from Spring Security
     * @return list of advertisement DTOs
     */

    @GetMapping("/ads/me")
    public ResponseEntity<AdsDto> getAdsMe(Principal principal) {
        logger.info("User login name: " + principal.getName());
        return advertisementsService.getAdsDtoByUserLoginName(principal.getName());
    }


    /**
     * <h2>PATCH /ads/{id}/image Обновление картинки объявления</h2>
     *
     * @param id advertisement identifier
     * @param image file with new photo
     * @return updated picture
     */
    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<byte[]> updateImage(@Parameter(name = "id", description = "user identifier")
                                                    @PathVariable(name = "id") long id,
                                              @Parameter(name = "image", description = "file with image")
                                              @RequestBody MultipartFile image) {

        ImageProcessResult imageProcessResult = advertisementsService.getPhotoByAdId(id);

        return new ResponseEntity<byte[]>(HttpStatus.OK);

    }

    @PostMapping(value = "/training/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> trainingEndpoint(@RequestBody CreateOrUpdateAdDto dto,
                                                   @RequestParam MultipartFile picture)

            throws IOException {
        if (dto == null) {
            return ResponseEntity.ok("Nothing in dto!!");
        }

        String response = "dto: " + dto.toString() + picture.getName();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/training-with-avatar-picture/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> trainingWithAvatarPicture(@RequestParam MultipartFile sourceFile,
                                                     @PathVariable("userId") Integer userId) {
        Images image = Images.builder()
                .pictureType(PictureType.USER_AVATAR)
                .userId(userId)
                .fileSize(sourceFile.getSize())
                .mediaType(sourceFile.getContentType())
                .build();

        try {
            image.setData(sourceFile.getBytes());
        } catch (IOException e) {
            logger.info(e.toString());
        }

        imagesRepository.save(image);

        return ResponseEntity.ok("trainingWithAvatarPicture: " + image.getPictureType());
    }

    @GetMapping(value = "/training-with-avatar-picture/{userId}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*" })
    byte[] viewPicture(@PathVariable Integer userId) {
        if (imagesRepository.findById(Long.valueOf(userId)).isPresent()) {
            return imagesRepository.findById(userId.longValue()).get().getData();
        }
        return null;
    }

}
