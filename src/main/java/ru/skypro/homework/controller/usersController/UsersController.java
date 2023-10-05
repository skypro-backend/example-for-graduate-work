package ru.skypro.homework.controller.usersController;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.usersDTO.UserDTO;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/{name}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("name") String name) {
        return ResponseEntity.ok(userService.getUserByName(name));

    }
    @PutMapping("/users/update")
    public void updateUser(@PathVariable("id") String id, @RequestParam("userName") String userName) {
        return UserService.updateUsers;
    }

@PatchMapping (value= "{id}/image"),consumes= MediaType.MULTIPART_FROM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@PatchMapping Long id,@RequestParam MultipartFile image) {

 return ResponseEntity.ok(userService.updateUserImage(id,image);

}
    }
