package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.*;
import ru.skypro.homework.service.AdsService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
@Tag(name = "Объявления", description = "Методы работы с объявлениями.")
public class AdsController {

    private final AdsService adsService;

    /**
     * GET /ads
     *
     * @return OK (status code 200)
     */
    @Operation(
            operationId = "getALLAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapper.class))
                    })
            }
    )
    @GetMapping()
    public ResponseEntity<ResponseWrapper<AdsDto>> getAllAds(@RequestParam(required = false) String title) {
        ResponseWrapper<AdsDto> ads = new ResponseWrapper<>(adsService.getAllAds(title));
        return ResponseEntity.ok(ads);
    }

    /**
     * POST /ads : addAds
     *
     * @param properties (required)
     * @param image      (required)
     * @return Created (status code 201)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "addAds",
            summary = "addAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdsDto> addAds(@NotNull Authentication authentication,
                                         @RequestPart("properties") @Valid @javax.validation.constraints.NotNull @NotBlank CreateAdsDto properties,
                                         @RequestPart("image") MultipartFile image
    ) {
        return ResponseEntity.ok(adsService.save(properties, authentication.getName(), image));
    }

    /**
     * GET /ads/me : getAdsMe
     *
     * @return OK (status code 200)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getAdsMeUsingGET",
            summary = "getAdsMe",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapper.class))
                    }),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping(
            value = "/me"
    )
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<AdsDto>> getAdsMeUsingGET(@NotNull Authentication authentication) {
        ResponseWrapper<AdsDto> ads = new ResponseWrapper<>(adsService.getAdsByUser(authentication.getName()));
        return ResponseEntity.ok(ads);
    }

    /**
     * GET /ads/{ad_pk}/comments : getComments
     *
     * @param adPk (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getComments",
            summary = "getComments",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapper.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping("/{ad_pk}/comments")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseWrapper<CommentDto>> getComments(@PathVariable("ad_pk") Integer adPk) {
        ResponseWrapper<CommentDto> ads = new ResponseWrapper<>(adsService.getAdsComments(adPk));
        return ResponseEntity.ok(ads);
    }

    /**
     * POST /ads/{ad_pk}/comments : addComments
     *
     * @param adPk    (required)
     * @param comment (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "addComments",
            summary = "addComments",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping("/{ad_pk}/comments")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk") Integer adPk,
                                                  @NotNull @Valid @RequestBody CommentDto comment,
                                                  Authentication authentication) {
        comment.setPk(adPk);
        return ResponseEntity.ok(adsService.addComment(adPk, comment, authentication));
    }

    /**
     * GET /ads/{ad_pk}/comments/{id} : getComments
     *
     * @param adPk (required)
     * @param id   (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getComments1",
            summary = "getComments",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping("/{ad_pk}/comments/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CommentDto> getComment(@PathVariable("ad_pk") Integer adPk,
                                                 @PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getAdsComment(adPk, id));
    }

    /**
     * DELETE /ads/{ad_pk}/comments/{id} : deleteComments
     *
     * @param adPk (required)
     * @param id   (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "deleteComments",
            summary = "deleteComments",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @DeleteMapping("/{ad_pk}/comments/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteComments(Authentication authentication,
                                               @PathVariable("ad_pk") Integer adPk,
                                               @PathVariable("id") Integer id) {
        adsService.deleteComment(adPk, id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * PATCH /ads/{ad_pk}/comments/{id} : updateComments
     *
     * @param adPk    (required)
     * @param id      (required)
     * @param comment (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "updateComments",
            summary = "updateComments",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = CommentDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping("/{ad_pk}/comments/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CommentDto> updateComments(Authentication authentication,
                                                     @PathVariable("ad_pk") Integer adPk,
                                                     @PathVariable("id") Integer id,
                                                     @Valid @RequestBody CommentDto comment) {
        return ResponseEntity.ok(adsService.updateAdsComment(adPk, id, comment, authentication));
    }

    /**
     * GET /ads/{id} : getFullAd
     *
     * @param id (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     */
    @Operation(
            operationId = "getAds",
            summary = "getFullAd",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = FullAdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            }
    )
    @GetMapping("/{id}")
  //  @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(adsService.getFullAds(id));
    }

    /**
     * DELETE /ads/{id} : removeAds
     *
     * @param id (required)
     * @return No Content (status code 204)
     * or Unauthorized (status code 401)
     * or Forbidden (status code 403)
     */
    @Operation(
            operationId = "removeAds",
            summary = "removeAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")
            }
    )
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> removeAds(Authentication authentication, @PathVariable("id") Integer id) {
        adsService.removeAds(id, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * PATCH /ads/{id} : updateAds
     *
     * @param id        (required)
     * @param createAds (required)
     * @return OK (status code 200)
     * or Not Found (status code 404)
     * or Forbidden (status code 403)
     * or Unauthorized (status code 401)
     */
    @Operation(
            operationId = "updateAds",
            summary = "updateAds",
            tags = {"Объявления"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id,
                                            @Valid @RequestBody CreateAdsDto createAds,
                                            Authentication authentication) {
        return ResponseEntity.ok(adsService.updateAds(id, createAds, authentication));
    }
}
