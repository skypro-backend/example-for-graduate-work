package com.skypro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;

/**
 * Конфигурация для настройки веб-слоя приложения.
 * <p>
 * В текущей версии инициализирует пути для раздачи статических ресурсов
 * (картинок объявлений и аватарок) через resource handlers.
 * Пути строятся из свойства {@code app.image.upload-path}.
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.image.upload-path:./uploads/}")
    private String uploadPath;

    private String adsDirUrl;
    private String usersDirUrl;

    /**
     * Инициализирует URL для папок с картинками объявлений и пользователей.
     * <p>
     * Преобразует относительный путь в абсолютный и нормализует его
     * для корректной работы в Windows и Unix-системах.
     * </p>
     */
    @PostConstruct
    public void init() {
        adsDirUrl = Paths.get(uploadPath + "ads/").toAbsolutePath().normalize().toUri().toString();
        usersDirUrl = Paths.get(uploadPath + "users/").toAbsolutePath().normalize().toUri().toString();
    }

}
