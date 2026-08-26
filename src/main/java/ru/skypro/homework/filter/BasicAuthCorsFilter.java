package ru.skypro.homework.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для настройки CORS заголовков при использовании Basic аутентификации.
 * Добавляет заголовок Access-Control-Allow-Credentials для поддержки
 * кросс-доменных запросов с учетными данными.
 *
 * <p>Этот фильтр является расширением OncePerRequestFilter и гарантирует,
 * что он будет выполнен только один раз за каждый запрос.</p>
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Обрабатывает HTTP запрос и добавляет необходимые CORS заголовки.
     * Устанавливает заголовок Access-Control-Allow-Credentials: true
     * для поддержки кросс-доменных запросов с Basic аутентификацией.
     *
     * @param httpServletRequest входящий HTTP запрос
     * @param httpServletResponse исходящий HTTP ответ
     * @param filterChain цепочка фильтров для продолжения обработки
     * @throws ServletException если происходит ошибка сервлета
     * @throws IOException если происходит ошибка ввода-вывода
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
