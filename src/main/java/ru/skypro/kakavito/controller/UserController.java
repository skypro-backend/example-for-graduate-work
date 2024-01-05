package ru.skypro.kakavito.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.NewPasswordDTO;
import ru.skypro.kakavito.dto.UpdateUserDTO;
import ru.skypro.kakavito.dto.UserDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.mappers.UserMapper;
import ru.skypro.kakavito.service.UserService;

import java.io.IOException;

/**
 * Класс для управления потоком данных при работе с пользователями
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Запрос на изменение пароля
     * @param newPasswordDTO
     * @param authentication
     */
    @PostMapping("/set_password")
    public ResponseEntity<String> setNewPassword(@RequestBody NewPasswordDTO newPasswordDTO,
                                                 @NonNull Authentication authentication) {
        if (userService.setNewPassword(authentication.getName(),
                newPasswordDTO.getCurrentPassword(),
                newPasswordDTO.getNewPassword())) {
            return ResponseEntity.ok("Password was update");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password don`t match!");
        }
    }

    /**
     * Запрос на получение информации об авторизированном пользователе
     * @return UserDTO
     * @see UserDTO
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyProfile(/*@PathVariable Long id*/) {
//        return ResponseEntity.ok(userService.getAuthorizedUser());
        return ResponseEntity.ok(userMapper.convertToUserDTO(userService.getAuthorizedUser()));
//                userService.findById(id);
    }

    /**
     * Запрос на редактирование данных зарегестрированного пользователя
     * @param updateUserDTO
     * @return UpdateUserDTO
     * @see UpdateUserDTO
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateMyProfile(@RequestBody UpdateUserDTO updateUserDTO) {
        if (updateUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateMyProfile(updateUserDTO);
        return ResponseEntity.ok(updateUserDTO);
    }

    /**
     * Запрос на редактирование аватара пользователя
     * @param image
     * @param userDetails
     * @throws IOException
     * @throws ImageSizeExceededException
     */
    @PatchMapping(path = "/me/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<?> updateMyImage(@RequestParam MultipartFile image,
                                           @AuthenticationPrincipal UserDetails userDetails)
            throws IOException, ImageSizeExceededException {
        userService.updateMyImage(image, userDetails);
        return ResponseEntity.ok().build();
    }

}
