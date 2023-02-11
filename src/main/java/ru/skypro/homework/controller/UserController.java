package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.loger.FormLogInfo;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
public class UserController {


  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Operation(summary = "setPassword")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(schema = @Schema())
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
  @PostMapping(value = "/setPassword")
  public ResponseEntity<NewPasswordDTO> setPassword(
      @RequestBody
      @NotBlank(message = "newPassword не должен быть пустым") NewPasswordDTO newPassword) {
    log.info(FormLogInfo.getInfo());
    NewPasswordDTO newPasswordDTO = userService.setPassword(newPassword);
    return ResponseEntity.ok(newPasswordDTO);
  }

  @Operation(summary = "getUser")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(schema = @Schema())
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
  @GetMapping(value = "/me")
  public ResponseEntity<UserDTO> getUser() {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.getUser());
  }

  @Operation(summary = "updateUser")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(schema = @Schema())
      ),
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
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @PatchMapping(value = "/me")
  public ResponseEntity<UserDTO> updateUser(
      @RequestBody
      @NotBlank(message = "updateUser не должен быть пустым") UserDTO userDto) {
    log.info(FormLogInfo.getInfo());
    return ResponseEntity.ok(userService.updateUser(userDto));
  }

  @Operation(summary = "updateUserImage")
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "OK",
          content = @Content(schema = @Schema())
      ),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found"
      )
  })
  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<byte[]> updateUserImage(@RequestBody MultipartFile image) {
    log.info(FormLogInfo.getInfo());

    UserDTO userDTO = new UserDTO();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(
        MediaType.parseMediaType(Objects.requireNonNull(image.getContentType())));
    headers.setContentLength(image.getSize());
    byte[] body = userService.updateUserImage(image);
    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(body);

  }


}