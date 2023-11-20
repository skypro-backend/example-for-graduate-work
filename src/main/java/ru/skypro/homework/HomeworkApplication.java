package ru.skypro.homework;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CleanService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

@SpringBootApplication
@OpenAPIDefinition
public class HomeworkApplication {

  public static void main(String[] args) {
    SpringApplication.run(HomeworkApplication.class, args);
  }

}
