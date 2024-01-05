package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

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
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<UserDto> getInformationUser(Principal principal) {
        logger.info("Principal.name: " + principal.getName());
        return ResponseEntity.ok(userService.getInfoUser(principal.getName()));

/*    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(userService.getInfoUser(), HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser user){
        return new ResponseEntity<>(new UpdateUser(), HttpStatus.OK); */
    }
}
