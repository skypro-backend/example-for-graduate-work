//package ru.skypro.homework.controller.users;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.skypro.homework.dto.auth.RegisterDto;
//import ru.skypro.homework.service.users.UsersService;
//
//@CrossOrigin(value = "http://localhost:3000")
//@Slf4j
//@RestController
//@RequestMapping("/users")
//public class UsersController {
//
//    private final UsersService usersService;
//
//    public UsersController(UsersService usersService) {
//        this.usersService = usersService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Void> register(@RequestBody RegisterDto registerDto) {
//        usersService.register(registerDto);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//
//    }
//
//
//
//}
