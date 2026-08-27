# Торговая площадка и биржа строительных услуг Sopetov

Дипломный проект по реализации бэкенда для веб-платформы (маркетплейса), где строительные компании, бригады и независимые мастера могут размещать свои профили и карточки услуг (объявления), а клиенты — находить исполнителей и оставлять отзывы.

## Используемые технологии
* **Java 17** & **Spring Boot 2.7.x**
* **Spring Security** (авторизация через БД, шифрование BCrypt, роли USER/ADMIN)
* **Spring Data JPA** & **PostgreSQL**
* **Liquibase** (управление миграциями и структурой базы данных)
* **MapStruct** (автоматическое преобразование Entity <-> DTO)
* **OpenAPI / Swagger** (документирование REST API)
* **JUnit 5** & **Mockito** (модульное тестирование бизнес-логики)

## Требования к окружению
* Установленный Docker Desktop
* IntelliJ IDEA (или аналогичная IDE)

## Инструкции по запуску

### 1. Запуск базы данных в Docker
Поднимите контейнер PostgreSQL 14 на порту 5433 одной командой в терминале:
```bash
docker run --name sopetov-postgres -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=sopetov_db -d postgres:14
```

### 2. Запуск Backend
1. Откройте проект в IntelliJ IDEA.
2. Проверьте корректность настроек в файле `src/main/resources/application.properties`.
3. Запустите главный класс `HomeworkApplication.java`. При старте Liquibase автоматически создаст все необходимые таблицы.
4. Документация API будет доступна по адресу: `http://localhost:8080/swagger-ui/index.html`

### 3. Запуск Frontend
1. Откройте Docker Desktop.
2. Во вкладке `Containers` запустите ранее настроенный контейнер фронтенда на порту `3000`.
3. Сайт откроется в браузере по адресу: `http://localhost:3000`
