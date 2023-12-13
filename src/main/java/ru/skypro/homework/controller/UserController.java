package ru.skypro.homework.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

  private final UserService userService;
  private final ImageService imageService;

  public UserController(UserService userService, ImageService imageService) {
    this.userService = userService;
    this.imageService = imageService;
  }


  @PostMapping("/set_password")
  public ResponseEntity<?> updatePassword(@RequestBody NewPassword newPassword, Authentication authentication) {

    if (userService.changePassword(newPassword, authentication.getName())) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/me")
  public ResponseEntity<?> getUser(Authentication authentication) {

    User userRetrieved = userService.retrieveAuthorizedInformation(authentication.getName());
    return ResponseEntity.ok(userRetrieved);
  }


  @PatchMapping("/me")
  public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
    UpdateUser user = userService.patchAuthorizedUserInformation(updateUser, authentication.getName());
    return ResponseEntity.ok(user);
  }



  @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile linkedPicture,Authentication authentication ) {
    userService.patchAuthorizedUserPicture(linkedPicture, authentication.getName());
    return ResponseEntity.ok().build();
  }

  @Transactional
  @GetMapping(value = "/{id}/avatar", produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,"image/*"})
  public byte[] getImage(@PathVariable Integer id) throws IOException {
    Image image = imageService.callImageById(id);
    return image.getImage();
  }
}
