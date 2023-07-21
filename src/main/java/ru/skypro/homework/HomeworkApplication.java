package ru.skypro.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = {"ru.skypro.homework"})
public class HomeworkApplication {
  public static void main(String[] args) {
    SpringApplication.run(HomeworkApplication.class, args);
  }
}
