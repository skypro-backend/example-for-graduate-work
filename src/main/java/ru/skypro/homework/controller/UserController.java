package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
//@Tag(name = "Объявления")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(implementation = Comment.class))
          }
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @GetMapping(value = "/{ad_pk}/comments/{id}")
  public ResponseEntity<Comment> getComments(@PathVariable(name = "ad_pk")
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
          responseCode = "200",
          description = "OK",
          content = {
              @Content(
                  schema = @Schema(implementation = Comment.class))
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
  @PatchMapping(value = "/{ad_pk}/comments/{id}")
  public ResponseEntity<Comment> updateComments(@PathVariable(name = "ad_pk")
  @NotBlank(message = "ad_pk не должен быть пустым")
  @Min(value = 1, message = "Идентификатор должен быть больше 0")
  @Parameter(description = "Идентификатор объявления",
      example = "1") String adPk,
      @PathVariable(name = "id")
      @NotBlank(message = "id не должен быть пустым")
      @Min(value = 1, message = "Идентификатор должен быть больше 0")
      @Parameter(description = "Идентификатор комментария",
          example = "1") int id,
      @RequestBody Comment comment) {
    return ResponseEntity.ok(adsService.updateComments(adPk, id, comment));
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
}