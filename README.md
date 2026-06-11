# Avito Clone Backend

Backend часть сервиса размещения объявлений, разработанная на Spring Boot.

## Возможности

### Пользователи

* Регистрация пользователя
* Авторизация пользователя
* Получение информации о текущем пользователе
* Обновление профиля
* Смена пароля
* Загрузка аватара пользователя

### Объявления

* Получение списка объявлений
* Получение объявления по идентификатору
* Создание объявления
* Обновление объявления
* Удаление объявления
* Получение объявлений текущего пользователя
* Загрузка изображений объявлений

### Комментарии

* Получение комментариев объявления
* Создание комментария
* Обновление комментария
* Удаление комментария

---

## Технологии

* Java 17
* Spring Boot 2.7
* Spring Security
* Spring Data JPA
* PostgreSQL 15
* Liquibase
* Docker
* Docker Compose
* Lombok
* Swagger OpenAPI

---

## Архитектура проекта

```text
src
├── controller
├── service
├── repository
├── model
├── dto
├── config
└── liquibase
```

### Основные слои

#### Controller

Принимает HTTP-запросы и возвращает ответы клиенту.

#### Service

Содержит бизнес-логику приложения.

#### Repository

Работает с базой данных через Spring Data JPA.

#### Model

JPA-сущности базы данных.

#### DTO

Объекты передачи данных между клиентом и сервером.

---

## База данных

Используется PostgreSQL.

Основные таблицы:

### users

| Поле       | Тип     |
| ---------- | ------- |
| id         | bigint  |
| email      | varchar |
| password   | varchar |
| first_name | varchar |
| last_name  | varchar |
| phone      | varchar |
| image      | varchar |
| role       | varchar |

### ads

| Поле        | Тип     |
| ----------- | ------- |
| id          | bigint  |
| title       | varchar |
| description | varchar |
| price       | integer |
| image       | varchar |
| author_id   | bigint  |

### comments

| Поле       | Тип       |
| ---------- | --------- |
| id         | bigint    |
| text       | varchar   |
| created_at | timestamp |
| author_id  | bigint    |
| ad_id      | bigint    |

---

## Запуск проекта через Docker

### Сборка и запуск

```bash
docker compose up --build
```

После запуска будут доступны:

Frontend:

http://localhost:3000

Backend:

http://localhost:8888

Swagger:

http://localhost:8888/swagger-ui/index.html

---

## Настройки приложения

Файл:

```properties
application.properties
```

Основные параметры:

```properties
spring.datasource.url=jdbc:postgresql://postgres:5432/avito
spring.datasource.username=neo
spring.datasource.password=*******
server.port=8888
images.dir=/app/images
```

---

## Работа с изображениями

Изображения пользователей и объявлений сохраняются на файловую систему.

Структура:

```text
images
├── users
└── ads
```

Для сохранения данных между перезапусками контейнеров рекомендуется использовать Docker Volume.

---

## Авторизация

Используется HTTP Basic Authentication.

После регистрации пользователь получает роль:

* USER
* ADMIN

---

## Liquibase

Миграции базы данных выполняются автоматически при запуске приложения.

Файл конфигурации:

```text
liquibase/lesson-three.yml
```

---

## API

### Пользователи

| Метод | URL                 |
| ----- | ------------------- |
| POST  | /register           |
| POST  | /login              |
| GET   | /users/me           |
| PATCH | /users/me           |
| POST  | /users/set_password |
| PATCH | /users/me/image     |

### Объявления

| Метод  | URL             |
| ------ | --------------- |
| GET    | /ads            |
| POST   | /ads            |
| GET    | /ads/me         |
| GET    | /ads/{id}       |
| PATCH  | /ads/{id}       |
| DELETE | /ads/{id}       |
| PATCH  | /ads/{id}/image |

### Комментарии

| Метод  | URL                            |
| ------ | ------------------------------ |
| GET    | /ads/{id}/comments             |
| POST   | /ads/{id}/comments             |
| PATCH  | /ads/{id}/comments/{commentId} |
| DELETE | /ads/{id}/comments/{commentId} |

---

## Автор

Выпускная квалификационная работа по разработке backend-приложения сервиса объявлений на Spring Boot.
