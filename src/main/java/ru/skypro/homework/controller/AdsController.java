package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.PropertiesDTO;
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
                  schema = @Schema(ref = "#/components/schemas/ResponseWrapper«Ads»"))
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
  @GetMapping
  public ResponseEntity<Collection<AdsDTO>> getALLAds() {
    return ResponseEntity.ok(adsService.getALLAds());
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
  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<AdsDTO> createAds(
      @RequestBody PropertiesDTO propertiesDTO,
      MultipartFile image) {
    return ResponseEntity.ok(adsService.addAds(propertiesDTO, image));
  }


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
      example = "1") String adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id) {
    return ResponseEntity.ok(adsService.getComments(adPk, id));
  }

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
      example = "1") String adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id) {
    adsService.deleteComments(adPk, id);
  }

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
  @GetMapping("/{ad_pk}/comment")
  public ResponseEntity<Collection<CommentDTO>> getAdsComments(
      @PathVariable(name = "ad_pk") @NonNull @Parameter(description = "Больше 0, Например 1") Integer pk) {
    return ResponseEntity.ok().body(adsService.getAdsComments(pk));
  }

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
  @PostMapping("/{ad_pk}/comment")
  public ResponseEntity<?> addAdsComments(
      @PathVariable(name = "ad_pk") @NonNull @Parameter(description = "Больше 0, Например 1") Integer pk) {
    adsService.addAdsComments(pk);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Удаление комментария пользователя")
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
      example = "1") String adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id,
      @RequestBody CommentDTO commentDTO) {
    return ResponseEntity.ok(adsService.updateComments(adPk, id, commentDTO));
  }

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
  @DeleteMapping("/{ad_pk}/comment/{id}")
  public ResponseEntity<?> deleteAdsComment(
      @PathVariable(name = "ad_pk") @NonNull @Parameter(description = "Больше 0, Например 1") Integer pk,
      @PathVariable(name = "id") @NonNull @Parameter(description = "Больше 0, Например 1") Integer id) {
    adsService.deleteAdsComment(pk, id);
    return ResponseEntity.ok().build();
  }


}
