package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.UserDto;

import ru.skypro.homework.service.UserService;
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
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

    public ResponseEntity<UserDto> getInformationUser() {
        return ResponseEntity.ok(userService.getInfoUser());

/*    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser user){
        return new ResponseEntity<>(new UpdateUser(), HttpStatus.OK); */
    }
}
