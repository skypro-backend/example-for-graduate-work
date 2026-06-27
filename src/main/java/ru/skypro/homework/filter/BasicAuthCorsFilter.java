package ru.skypro.homework.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для добавления CORS заголовков, необходимых для работы базовой аутентификации.
 * Наследуется от {@link OncePerRequestFilter} для выполнения один раз за HTTP-запрос.
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Метод фильтрации, добавляющий заголовок разрешения передачи учетных данных (Credentials).
     *
     * @param httpServletRequest входящий HTTP-запрос
     * @param httpServletResponse исходящий HTTP-ответ
     * @param filterChain цепочка фильтров
     * @throws ServletException в случае внутренних ошибок сервлета
     * @throws IOException в случае ошибок ввода-вывода
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}