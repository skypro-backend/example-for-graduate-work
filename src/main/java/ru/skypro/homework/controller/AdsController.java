package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.service.AdsService;

@RestController
@RequestMapping("/ads")
@Tag(name = "Объявления")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

  private final AdsService adsService;

  public AdsController(AdsService adsService) {
    this.adsService = adsService;
  }

  @Operation(summary = "Получить все объявления")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperAds.class)))
          }
      )
  })
  @GetMapping
  public ResponseEntity<ResponseWrapperAds> getAds() {
    return ResponseEntity.ok(adsService.getAds());
  }

  @Operation(summary = "Добавить объявление")
  @ApiResponses({
      @ApiResponse(
          responseCode = "201",
          description = "OK",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = AdsDTO.class)))
          }
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      )
  })
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<AdsDTO> createAds(
      @RequestPart("image") MultipartFile file,
      @RequestPart("properties") @Valid CreateAds createAds,
      Authentication authentication) throws IOException {
    return ResponseEntity.ok(adsService.addAds(createAds, file, authentication));
  }

  @Operation(summary = "Получить комментарий по id объявления и id комментария")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(implementation = CommentDTO.class))
          }
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @GetMapping(value = "/{ad_pk}/comments/{id}")
  public ResponseEntity<CommentDTO> getComments(@PathVariable(name = "ad_pk")
  @NotBlank(message = "ad_pk не должен быть пустым")
  @Min(value = 1, message = "Идентификатор должен быть больше 0")
  @Parameter(description = "Идентификатор объявления",
      example = "1") int adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id) {
    return ResponseEntity.ok(adsService.getComments(adPk, id));
  }

  @Operation(summary = "Удалить комментарий по id объявления и id комментария")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK"
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @DeleteMapping(value = "/{ad_pk}/comments/{id}")
  public void deleteComments(@PathVariable(name = "ad_pk")
  @NotBlank(message = "ad_pk не должен быть пустым")
  @Min(value = 1, message = "Идентификатор должен быть больше 0")
  @Parameter(description = "Идентификатор объявления",
      example = "1") Integer adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") Integer id) {
    adsService.deleteComments(adPk, id);
  }

  @Operation(summary = "Удалить объявление по id")
  @ApiResponses({
      @ApiResponse(
          responseCode = "204",
          description = "No Content"
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      )
  })
  @DeleteMapping(value = "/{id}")
  public void removeAds(@PathVariable(name = "id")
  @NotBlank(message = "id не должен быть пустым")
  @Min(value = 1, message = "Идентификатор должен быть больше 0")
  @Parameter(description = "Идентификатор объявления",
      example = "1") int id) {
    adsService.removeAds(id);
  }

  @Operation(summary = "Получить комментарии объявления")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(implementation = ResponseWrapperComment.class))
          }
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      )
  })
  @GetMapping("/{ad_pk}/comments")
  public ResponseEntity<ResponseWrapperComment> getAdsComments(
      @PathVariable(name = "ad_pk") @NonNull @Parameter(description = "Больше 0, Например 1") Integer pk) {
    return ResponseEntity.ok().body(adsService.getAdsComments(pk));
  }


  @Operation(summary = "Добавить комментарии к объявлению")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(implementation = CommentDTO.class))
          }
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = {@Content(array = @ArraySchema(schema = @Schema()))}
      )
  })
  @PostMapping("/{ad_pk}/comments")
  public ResponseEntity<CommentDTO> addAdsComments(
      @PathVariable(name = "ad_pk") @NonNull @Parameter(description = "Больше 0, Например 1") Integer pk,
      @RequestBody @Valid CommentDTO commentDTO,
      Authentication authentication) {
    return ResponseEntity.ok(adsService.addAdsComments(pk, commentDTO, authentication));
  }

  @Operation(summary = "Изменение комментария пользователя")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "CommentDTO added",
          content = {
              @Content(
                  mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = CommentDTO.class)))
          }),
  })
  @PatchMapping(value = "/{ad_pk}/comments/{id}")
  public ResponseEntity<CommentDTO> updateComments(@PathVariable(name = "ad_pk")
  @NotBlank(message = "ad_pk не должен быть пустым")
  @Min(value = 1, message = "Идентификатор должен быть больше 0")
  @Parameter(description = "Идентификатор объявления",
      example = "1") int adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id,
      @RequestBody CommentDTO commentDTO) {
    return ResponseEntity.ok(adsService.updateComments(adPk, id, commentDTO));
  }

  @Operation(summary = "Получить объявление")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = FullAds.class)))
          }
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @GetMapping("{id}")
  public ResponseEntity<?> getAds(
      @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id) {
    return ResponseEntity.ok().body(adsService.getAdById(id));
  }

  @Operation(summary = "Обновить объявление")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(ref = "#/components/schemas/AdsDTO"))
          }
      ),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized"
      ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @PatchMapping("{id}")
  public ResponseEntity<?> updateAds(
      @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id,
      @RequestBody CreateAds createAds) {

    return ResponseEntity.ok().body(adsService.updateAds(id, createAds));
  }


  @Operation(summary = "Получаем только свои объявления")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperAds.class)))
          }
      )
  })
  @GetMapping("/me")
  public ResponseEntity<ResponseWrapperAds>
  getAdsMe(Authentication authentication) {
    return ResponseEntity.ok(adsService.getAdsMe(authentication));
  }

}
