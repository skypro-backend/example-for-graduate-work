package ru.skypro.homework.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс является компонентом и фильтром для обработки HTTP-запросов
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Метод {@code doFilterInternal} выполняет фактическую обработку запросов, добавляя заголовок
     * "Access-Control-Allow-Credentials" со значением "true" ко всем HTTP-ответам.
     *
     * @param httpServletRequest  объект запроса HTTP
     * @param httpServletResponse объект ответа HTTP
     * @param filterChain         цепочка фильтров, через которую проходит запрос
     * @throws ServletException если происходит ошибка в обработке запроса
     * @throws IOException      если происходит ошибка ввода/вывода при обработке запроса
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
