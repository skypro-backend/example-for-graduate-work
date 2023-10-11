package ru.skypro.homework.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.skypro.homework.dto.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<PasswordUpdateResponseDTO> updatePassword(@RequestBody PasswordUpdateRequestDTO passwordUpdateRequestDTO) {

        PasswordUpdateResponseDTO responseDTO = new PasswordUpdateResponseDTO("test1", "test2");
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {

        UserDTO userDTO = new UserDTO();

        userDTO.setUserID(1L);
        userDTO.setEmail("string");
        userDTO.setFirstName("string");
        userDTO.setLastName("string");
        userDTO.setPhone("string");
        userDTO.setRole(Role.USER);

        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/me")
        public ResponseEntity<UpdateUserRequestDTO> updateUserInfo(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {

            UpdateUserRequestDTO updatedUser = new UpdateUserRequestDTO();
            updatedUser.setFirstName("test");
            updatedUser.setLastName("test");
            updatedUser.setPhone("test");

            return ResponseEntity.ok(updatedUser);
    }


}
