# Проект "Доска объявлений" (SkyPro Homework)

##  Назначение проекта

Веб-приложение для размещения и управления объявлениями. Пользователи могут создавать объявления, оставлять комментарии, управлять своим профилем и аватаром. Приложение разработано в рамках курса SkyPro и демонстрирует использование современных технологий Java-разработки.

##  Технологии

### Backend
- **Java 11** - основной язык программирования
- **Spring Boot 2.7.15** - фреймворк для создания приложения
- **Spring Security** - аутентификация и авторизация
- **Spring Data JPA** - работа с базой данных
- **PostgreSQL** - реляционная база данных
- **Liquibase** - управление миграциями базы данных
- **MapStruct** - маппинг между DTO и Entity
- **Maven** - управление зависимостями и сборка
- **OpenAPI 3.0 (Swagger)** - документация API

### Frontend
- **React** - библиотека для создания пользовательских интерфейсов
- **JavaScript/TypeScript** - язык программирования

##  Требования к системе

- **Java 11** или выше
- **Maven 3.6+**
- **PostgreSQL 14+**
- **Node.js 14+** (для frontend)

##  Настройка базы данных

### Создание базы данных

sql
CREATE DATABASE skypro_homework;
## Настройка подключения в application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/skypro_homework
spring.datasource.username=postgres
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver
## Запуск приложения
Backend
# Сборка проекта
mvn clean package

# Запуск приложения
mvn spring-boot:run
Frontend
# Установка зависимостей
npm install

# Запуск development сервера
npm start

## Документация API 
Swagger UI
http://localhost:8080/swagger-ui.html
## Основные эндпоинты
Публичные (доступны без авторизации)
POST	/register	Регистрация нового пользователя
POST	/login	Авторизация пользователя
GET	/ads	Получение всех объявлений
GET	/ads/{id}	Получение объявления по ID
GET	/images/**	Получение изображений
Требуют авторизации (USER, ADMIN)

POST	/ads	Создание нового объявления
PATCH	/ads/{id}	Обновление объявления
DELETE	/ads/{id}	Удаление объявления
GET	/users/me	Получение информации о пользователе
PATCH	/users/me	Обновление данных пользователя
## Аунтификация 
Приложение использует Basic Authentication:
Authorization: Basic base64(username:password)

## Тестовые пользователи
USER	- user@gmail.com	password
ADMIN -	admin@mail.com	password

## Структура проекта
graduate-work2/
├── src/
│   ├── main/
│   │   ├── java/ru/skypro/homework/
│   │   │   ├── config/          # Конфигурации
│   │   │   ├── controller/      # REST контроллеры
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── entity/          # Сущности базы данных
│   │   │   ├── exception/       # Обработка исключений
│   │   │   ├── mapper/          # MapStruct мапперы
│   │   │   ├── repository/      # JPA репозитории
│   │   │   └── service/         # Бизнес-логика
│   │   └── resources/
│   │       ├── db/changelog/    # Liquibase миграции
│   │       └── application.properties
│   └── test/                    # Тесты
├── frontend/                    # React приложение
├── uploads/                     # Директория для изображений
├── pom.xml                      # Maven конфигурация
└── README.md

## Основные возможности
--Пользователи
Регистрация и авторизация
Просмотр и редактирование профиля
Загрузка аватара
Смена пароля
--Объявления
Создание объявлений с картинками
Просмотр всех объявлений
Редактирование и удаление своих объявлений
--Комментарии
Добавление комментариев
Просмотр комментариев
Редактирование и удаление своих комментариев

## Возможные проблемы 
Ошибка подключения к БД
Проверьте правильность данных в application.properties
Ошибка компиляции MapStruct
mvn clean compile

## Авторы 
Лилия Пестрякова
ADMIN - может управлять любыми объявлениями и комментариями

Публичные GET запросы на объявления и изображения доступны всем
