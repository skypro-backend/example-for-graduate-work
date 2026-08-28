package ru.skypro.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения "Доска объявлений".
 * Точка входа в Spring Boot приложение.
 *
 * <p>Приложение предоставляет REST API для управления объявлениями,
 * комментариями и пользователями. Использует Spring Boot, Spring Security,
 * Spring Data JPA и PostgreSQL.</p>
 *
 * <p>Основные функции:
 * <ul>
 *   <li>Регистрация и аутентификация пользователей</li>
 *   <li>Создание, просмотр, обновление и удаление объявлений</li>
 *   <li>Добавление и управление комментариями</li>
 *   <li>Загрузка и отображение изображений</li>
 *   <li>Управление профилем пользователя</li>
 * </ul>
 * </p>
 *
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
public class HomeworkApplication {

  /**
   * Точка входа в приложение.
   * Запускает Spring Boot приложение с автоматической конфигурацией.
   *
   * @param args аргументы командной строки (не используются)
   */
  public static void main(String[] args) {
    SpringApplication.run(HomeworkApplication.class, args);
  }
}
