# Ads Online

Дипломный проект курса **Java-разработчик**.

REST API для сервиса размещения объявлений с регистрацией пользователей, авторизацией, комментариями и загрузкой изображений.

---

## Возможности приложения

- регистрация пользователя;
- авторизация по HTTP Basic;
- смена пароля;
- просмотр собственного профиля;
- редактирование профиля;
- загрузка аватара пользователя;
- создание объявлений;
- просмотр всех объявлений;
- просмотр собственного списка объявлений;
- изменение объявления;
- удаление объявления;
- загрузка изображения объявления;
- получение изображения объявления;
- добавление комментариев;
- изменение комментариев;
- удаление комментариев;
- разграничение прав USER и ADMIN.

---

## Используемые технологии

- Java 17
- Spring Boot 2.7
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- Liquibase
- Lombok
- Maven
- Swagger OpenAPI

---

## Архитектура проекта

```
Controller
     ↓
Service
     ↓
Repository
     ↓
PostgreSQL
```

Используется классическая трехслойная архитектура.

---

## Структура проекта

```
config
controller
dto
entity
exception
mapper
repository
service
```

---

## Настройка PostgreSQL

Создать базу данных:

```sql
CREATE DATABASE ads;
```

Создать переменную окружения:

Windows PowerShell

```powershell
$env:POSTGRES_PASSWORD=ваш_пароль
```

---

## application.properties

```properties
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/ads
spring.datasource.username=postgres
spring.datasource.password=${POSTGRES_PASSWORD}
```

---

## Запуск проекта

Компиляция

```bash
./mvnw clean compile
```

Запуск

```bash
./mvnw spring-boot:run
```

После успешного запуска приложение будет доступно по адресу

```
http://localhost:8080
```

---

## Swagger

Документация API

```
http://localhost:8080/swagger-ui.html
```

OpenAPI

```
http://localhost:8080/v3/api-docs
```

---

## Основные REST API

### Авторизация

```
POST /register
POST /login
```

### Пользователь

```
GET    /users/me
PATCH  /users/me
POST   /users/set_password
PATCH  /users/me/image
```

### Объявления

```
GET    /ads
GET    /ads/{id}
POST   /ads
PATCH  /ads/{id}
DELETE /ads/{id}

GET    /ads/me

PATCH  /ads/{id}/image
GET    /ads/{id}/image
```

### Комментарии

```
GET    /ads/{id}/comments
POST   /ads/{id}/comments

PATCH  /ads/{adId}/comments/{commentId}
DELETE /ads/{adId}/comments/{commentId}
```

---

## Роли пользователей

### USER

- создание объявлений;
- редактирование собственных объявлений;
- удаление собственных объявлений;
- работа со своими комментариями.

### ADMIN

- редактирование и удаление любых объявлений;
- редактирование и удаление любых комментариев.

## База данных

Основные таблицы

- app_user
- ad
- comment

Миграции выполняются автоматически при запуске приложения с помощью Liquibase.

---

## Автор

Дипломный проект выполнен в рамках обучения на курсе **Java-разработчик**.
