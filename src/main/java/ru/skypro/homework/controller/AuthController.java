package ru.skypro.homework.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

/**
 * Контроллер для обработки запросов аутентификации и регистрации.
 * Предоставляет REST API для входа в систему и создания новых пользователей.
 */
// @CrossOrigin(value = "http://localhost:3000")
@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Обрабатывает запрос на аутентификацию пользователя.
     * Проверяет учетные данные и выполняет вход в систему.
     *
     * @param login DTO с логином и паролем пользователя
     * @return ResponseEntity с кодом 200 OK при успешной аутентификации,
     *         или 401 Unauthorized при неудаче
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        log.debug("Login request for user: {}", login.getUsername());
        if (authService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Обрабатывает запрос на регистрацию нового пользователя.
     * Создает учетную запись с предоставленными данными.
     *
     * @param register DTO с данными для регистрации
     * @return ResponseEntity с кодом 201 Created при успешной регистрации,
     *         или 400 Bad Request при ошибке
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        log.debug("Register request for user: {}", register.getUsername());
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
