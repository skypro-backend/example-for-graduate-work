package ru.skypro.homework.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.repository.RoleRepository;

/**
 * Компонент для автоматической инициализации данных при запуске приложения.
 * Этот класс реализует интерфейс CommandLineRunner, что позволяет выполнить код
 * сразу после загрузки контекста Spring Boot.
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    /**
     * Метод, который вызывается при запуске приложения.
     * В нем происходит проверка наличия ролей "USER" и "ADMIN" в базе данных,
     * и если их нет — они создаются.
     *
     * @param args аргументы командной строки (не используются в данном случае).
     * @throws Exception возможное исключение, если что-то пойдет не так.
     */
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new RoleEntity(null, "USER"));
        }
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new RoleEntity(null, "ADMIN"));
        }
    }
}
