package com.skypro.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Фильтр для добавления CORS-заголовка {@code Access-Control-Allow-Credentials}.
 * <p>
 * Этот фильтр выполняется один раз за запрос и добавляет заголовок,
 * позволяющий фронтенду отправлять учётные данные (например, cookies)
 * при кросс-доменных запросах.
 * </p>
 * <p>
 * Фильтр зарегистрирован в цепочке Spring Security и срабатывает
 * до выполнения основной логики контроллера.
 * </p>
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    /**
     * Выполняет фильтрацию запроса, добавляя заголовок
     * {@code Access-Control-Allow-Credentials: true}.
     *
     * @param httpServletRequest  входящий HTTP-запрос
     * @param httpServletResponse исходящий HTTP-ответ
     * @param filterChain         цепочка фильтров для продолжения обработки
     * @throws ServletException если возникает ошибка при обработке запроса
     * @throws IOException      если возникает ошибка ввода-вывода
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
