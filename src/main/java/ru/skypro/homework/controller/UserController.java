package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/set_password")
  @Operation(
          summary = "Обновление пароля",
          responses = {
                  @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),

          }
  )
  public ResponseEntity<?> updatePassword(@RequestBody NewPassword newPassword, Authentication authentication) {
    if (userService.changePassword(newPassword, authentication.getName())) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/me")
  @Operation(
          summary = "Получение информации об авторизованном пользователе",
          responses = {
    @ApiResponse(responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class)
            )),
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
  }
  )
  public ResponseEntity<?> getUser(Authentication authentication) {
    User userRetrieved = userService.retrieveAuthorizedInformation(authentication.getName());
    return ResponseEntity.ok(userRetrieved);
  }


  @PatchMapping("/me")
  @Operation(
          summary = "Обновление информации об авторизованном пользователе",
          responses = {
                  @ApiResponse(responseCode = "200",
                          content = @Content(
                                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                                  schema = @Schema(implementation = User.class)
                          )),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
          }
  )
  public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
    UpdateUser updateUser1 = userService.patchAuthorizedUserInformation(updateUser, authentication.getName());
    return ResponseEntity.ok(updateUser1);
  }



  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
          summary = "Обновление аватара авторизованного пользователя",
          responses = {
                  @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(hidden = true))),
                  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
          }
  )
  public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile image) {
    return ResponseEntity.ok().build();
  }
}
