package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.UsersService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@AllArgsConstructor
@Tag(name = "Объявления")
public class AdsController {

    private final AdsService adsService;
    private final UsersService usersService;

    // Получить все объявления
    @Operation(
            summary = "Получить все объявления", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AddDefaultCharsetFilter.ResponseWrapper.class))})
            }
    )
    @GetMapping()
    ResponseEntity<ResponseWrapperAdsDTO> getAllAds() {

        return ResponseEntity.ok(adsService.getAllAds());
    }

    // Добавить объявление
    @Operation(
            summary = "Добавить объявление", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Created",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdsDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<AdsDTO> postAd(@Valid
                                  @RequestPart("properties") CreateAdsDTO properties, @Valid
                                  @RequestPart("image") MultipartFile image,
                                  Authentication authentication) {
        UserDTO userDTO = usersService.getAuthorisedUser(authentication.getName());
        return ResponseEntity.ok(adsService.postAd(properties, image, userDTO.getId()));
    }

    // Получить информацию об объявлении
    @Operation(
            summary = "Получить информацию об объявлении", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FullAdsDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @GetMapping("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<FullAdsDTO> getAdInfo(@PathVariable(name = "id") Long adId) {
        return ResponseEntity.ok(adsService.getAdInfo(adId));
    }

    // Удалить объявление
    @Operation(
            summary = "Удалить объявление", tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<?> deleteAd(@PathVariable(name = "id") Long adId) {
        adsService.deleteAd(adId);
        return ResponseEntity.ok().build();
    }

    // Обновить информацию об объявлении
    @Operation(
            summary = "Обновить информацию об объявлении", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdsDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("/{id}")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<AdsDTO> updateAd(@PathVariable(name = "id") Long adId,
                                    @RequestBody CreateAdsDTO createAdsDTO) {


        return ResponseEntity.ok(adsService.updateAd(adId, createAdsDTO));
    }

    // Получить объявления авторизованного пользователя
    @Operation(
            summary = "Получить объявления авторизованного пользователя", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseWrapperAdsDTO.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content), //где получить?
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @GetMapping("/me")
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<ResponseWrapperAdsDTO> getAuthorisedUserAds(Authentication authentication) {
        UserDTO dto = usersService.getAuthorisedUser(authentication.getName());
        return ResponseEntity.ok(adsService.getAuthorisedUserAds(dto.getId()));
    }

    // Обновить картинку объявления
    @Operation(
            summary = "Обновить картинку объявления", tags = "Объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping(value = "/{id}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @RolesAllowed({"USER", "ADMIN"})
    ResponseEntity<byte[]> updateAdImage(@PathVariable Long id,
                                         @RequestPart("image") MultipartFile image) {
        byte[] bytes = adsService.updateAdImage(id, image);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE));
        headers.setContentLength(bytes.length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(bytes);
    }
}
