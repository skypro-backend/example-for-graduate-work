package ru.skypro.homework.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.skypro.homework.entity.RoleEntity;
import ru.skypro.homework.repository.RoleRepository;

/**
 * Автоматическая инициализация данных при запуске приложения
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

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
