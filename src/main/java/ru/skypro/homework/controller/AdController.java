package ru.skypro.homework.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.model.Ad;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@RestController
@CrossOrigin(value = "*")
@Tag(name = "\uD83D\uDE4B Объявления")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    // --------------------------------------------------------------------------------------
    //Получить все объявления
    @ApiOperation(
            value = "Получить все объявления",
            nickname = "getAds",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value = {
                    @ApiResponse(
                            code = 200,
                            message = "OK",
                            response =  AdsDTO.class
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads"
    )
    @GetMapping
    public ResponseEntity<AdsDTO> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }
    // --------------------------------------------------------------------------------------

    // Добавить объявление
    @ApiOperation(
            value = "Добавление объявления",
            nickname = "addAd",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Created",
                    response = AdDTO.class
            ),
            @ApiResponse(
                    code = 401,
                    message = "Unauthorized"
            ),
            @ApiResponse(
                    code = 403,
                    message = "Forbidden"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Not Found"
            )
    })
    @PostMapping(value = "/ads", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AdDTO> addAds(
            @RequestBody MultipartFile image,
            @RequestPart (value = "createOrUpdateAdDTO", required = true) CreateOrUpdateAdDTO createOrUpdateAdDTO)
            throws IOException {
        Ad ad = AdMapper.INSTANCE.createOrUpdateAdDTOToAd(createOrUpdateAdDTO);
        return ResponseEntity.ok(adService.addAd(ad,image));
    }
    // --------------------------------------------------------------------------------------


    // Получить информацию об объявлении

    @ApiOperation(
            value = "Получить информацию об объявлении",
            nickname = "getAdInfo",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value = {
                    @ApiResponse(
                            code = 200,
                            message = "OK",
                            response = AdsDTO.class
                    ),
                    @ApiResponse(
                            code = 404,
                            message = "Not Found"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads/{adId}"
    )
    public ResponseEntity<ExtendedAdDTO> getAdInfo(@PathVariable long adId) {
        return ResponseEntity.ok(adService.getAdInfo(adId));
    }
    // --------------------------------------------------------------------------------------

    // Удаалить информацию об объявлении

    @ApiOperation(
            value = "Удалить объявление",
            nickname = "getAdInfo",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value ={
                    @ApiResponse(
                            code = 204,
                            message = "No Content"
                    ),
                    @ApiResponse(
                            code = 401,
                            message = "Unauthorized"
                    ),
                    @ApiResponse(
                            code = 403,
                            message = "Forbidden"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/ads/{adId}"
    )
    public ResponseEntity<Void> removeAds(@PathVariable Long adId) {
        return ResponseEntity.ok(adService.deleteAd(adId));
    }
    // --------------------------------------------------------------------------------------

    //Обновить информацию об объявлении
    @ApiOperation(
            value = "Обновить информацию об объявлении",
            nickname = "updateAds",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value ={
                    @ApiResponse(
                            code = 200,
                            message = "OK",
                            response = AdDTO.class
                    ),
                    @ApiResponse(
                            code = 401,
                            message = "Unauthorized"
                    ),
                    @ApiResponse(
                            code = 403,
                            message = "Forbidden"
                    ),
                    @ApiResponse(
                            code = 404,
                            message = "Not Found"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/ads/{adId}",
            consumes = { "application/json" }
    )
    public ResponseEntity<AdDTO> updateAds(@PathVariable Long adId, @RequestBody CreateOrUpdateAdDTO createOrUpdateAdDTO) {
        return ResponseEntity.ok(adService.patchAd(adId, createOrUpdateAdDTO));
    }
    // --------------------------------------------------------------------------------------

    //Получить объявления авторизованного пользователя

    @ApiOperation(
            value = "Получить объявления авторизованного пользователя",
            nickname = "getAdsMe",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value = {
                    @ApiResponse(
                            code = 200,
                            message = "OK",
                            response = AdsDTO.class
                    ),
                    @ApiResponse(
                            code = 401,
                            message = "Unauthorized"
                    ),
                    @ApiResponse(
                            code = 403,
                            message = "Forbidden"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/ads/me"
    )
    public ResponseEntity<AdsDTO> getAdsMe() {
        return ResponseEntity.ok(adService.getAllAdsByAuthor());
    }
    // --------------------------------------------------------------------------------------

    @ApiOperation(
            value = "Обновить картинку объявления",
            nickname = "updateAdImage",
            notes = "",
            response = Ad.class, tags={ "Объявления", })
    @ApiResponses(value ={
                    @ApiResponse(
                            code = 200,
                            message = "OK",
                            response = Byte.class
                    ),
                    @ApiResponse(
                            code = 404,
                            message = "Not Found"
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/ads/{adId}/image",
            consumes = { "multipart/form-data" }
    )
    public ResponseEntity<String> updateAdImage(@PathVariable Long adId, @RequestPart MultipartFile image){

        return ResponseEntity.ok(adService.patchAdImage(adId, image));
    }

    // ======================================================================================

    // --------------------------------------------------------------------------------------
}