package ru.skypro.homework.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для добавления CORS-заголовков в HTTP-ответ.
 * Используется для поддержки работы с авторизацией (Basic Auth / cookies).
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Фильтр для добавления CORS-заголовков в HTTP-ответ.
     * Используется для поддержки работы с авторизацией (Basic Auth / cookies).
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
