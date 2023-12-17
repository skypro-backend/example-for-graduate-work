package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * <h2>Получение информации об авторизованном пользователе</h2>
     *       operationId: getUser
     *
     * @return responses:
     *      '200': content:
     *      application/json: User, description: OK
     *      <br>'401': description: Unauthorized
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser user){
        return new ResponseEntity<>(new UpdateUser(), HttpStatus.OK);
    }
}
