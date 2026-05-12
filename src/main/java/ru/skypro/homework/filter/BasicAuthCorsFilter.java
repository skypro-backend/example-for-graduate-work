package ru.skypro.homework.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.skypro.homework.config.WebSecurityConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр CORS с поддержкой учётных данных (credentials).
 * <p>
 * Добавляет заголовок {@code Access-Control-Allow-Credentials: true} в каждый ответ,
 * что позволяет браузеру отправлять учётные данные (например, авторизационные куки или заголовки)
 * при кросс-доменных запросах.
 * <p>
 * Это необходимо, потому что:
 * <ul>
 *   <li>Фронтенд работает на {@code http://localhost:3000}</li>
 *   <li>Бэкенд — на {@code http://localhost:8080}</li>
 *   <li>Между ними кросс-доменный запрос (CORS)</li>
 *   <li>Для передачи данных аутентификации (например, HTTP Basic) требуется разрешение на credentials</li>
 * </ul>
 * <p>
 * Без этого фильтра браузер блокирует запросы с авторизацией.
 *
 * @see WebSecurityConfig#corsConfigurationSource() — основная настройка CORS
 * @see OncePerRequestFilter — гарантирует однократное выполнение фильтра на каждый запрос
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Метод, выполняющий логику фильтрации.
     * <p>
     * Добавляет заголовок {@code Access-Control-Allow-Credentials: true}
     * и передаёт управление следующему фильтру в цепочке.
     *
     * @param httpServletRequest  входящий HTTP-запрос
     * @param httpServletResponse исходящий HTTP-ответ
     * @param filterChain         цепочка фильтров Spring Security
     * @throws ServletException если произошла ошибка сервлета
     * @throws IOException      если произошла ошибка ввода-вывода
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}