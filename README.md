# Ads Online Backend

Backend-часть учебного проекта по реализации сервиса объявлений. Приложение реализует REST API по контракту `openapi.yaml` и работает с готовым frontend-приложением.

## Стек

- Java 11
- Spring Boot 2.7
- Spring Web
- Spring Security, Basic Auth
- Spring Data JPA, Hibernate
- PostgreSQL
- Liquibase
- MapStruct
- Lombok
- JUnit 5, Mockito
- Docker

## Возможности

- регистрация и авторизация пользователей;
- получение списка объявлений;
- создание, редактирование и удаление объявлений;
- загрузка и получение изображений объявлений;
- получение и редактирование профиля пользователя;
- смена пароля;
- загрузка и получение аватарки пользователя;
- добавление, редактирование и удаление комментариев;
- разграничение прав для ролей `USER` и `ADMIN`.

## Роли и доступ

- `GET /ads` доступен без авторизации.
- Создание, редактирование и удаление объявлений доступны авторизованным пользователям.
- Пользователь с ролью `USER` может изменять и удалять только свои объявления и комментарии.
- Пользователь с ролью `ADMIN` может изменять и удалять чужие объявления и комментарии.
- Аутентификация реализована через Basic Auth.

## Хранение данных

Данные хранятся в PostgreSQL.

Основные таблицы:

- `users` — пользователи;
- `ads` — объявления;
- `comments` — комментарии.

Миграции базы данных находятся в:

```text
src/main/resources/db/changelog/changelog-master.sql
```

Изображения хранятся в файловой системе в директории `uploads`. В базе данных сохраняется путь к изображению.

## Настройки

Основной конфигурационный файл:

```text
src/main/resources/application.properties
```

По умолчанию backend подключается к PostgreSQL по адресу:

```text
jdbc:postgresql://localhost:5433/graduate_avito
```

Параметры БД:

```text
database: graduate_avito
username: postgres
password: postgres
port: 5433
```

## Запуск PostgreSQL

```powershell
docker compose up -d
```

Проверить контейнеры:

```powershell
docker ps
```

## Запуск backend

```powershell
.\mvnw.cmd spring-boot:run
```

Backend будет доступен по адресу:

```text
http://localhost:8080
```

## Запуск frontend

Frontend запускается из готового Docker-образа:

```powershell
docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21
```

Frontend будет доступен по адресу:

```text
http://localhost:3000
```

Если используется антивирус или антибаннер, нужно отключить блокировку рекламы для `localhost:3000`, так как frontend использует CSS-классы вида `ads` и `ad`.

## Дефолтный администратор

При применении миграций создается администратор:

```text
login: admin@gmail.com
password: password
role: ADMIN
```

## Основные endpoints

Авторизация и регистрация:

```text
POST /register
POST /login
```

Пользователи:

```text
GET   /users/me
PATCH /users/me
POST  /users/set_password
PATCH /users/me/image
GET   /users/{id}/image
```

Объявления:

```text
GET    /ads
POST   /ads
GET    /ads/me
GET    /ads/{id}
PATCH  /ads/{id}
DELETE /ads/{id}
GET    /ads/{id}/image
PATCH  /ads/{id}/image
```

Комментарии:

```text
GET    /ads/{id}/comments
POST   /ads/{id}/comments
PATCH  /ads/{adId}/comments/{commentId}
DELETE /ads/{adId}/comments/{commentId}
```

Полный контракт API находится в файле:

```text
openapi.yaml
```

## Тесты

Запуск тестов:

```powershell
.\mvnw.cmd test
```

Покрыты основные сценарии:

- регистрация и авторизация;
- проверка прав при работе с объявлениями;
- проверка прав при работе с комментариями;
- запуск Spring-контекста.

## Проверка работы через UI

Рекомендуемый сценарий проверки:

1. Запустить PostgreSQL.
2. Запустить backend.
3. Запустить frontend.
4. Открыть `http://localhost:3000`.
5. Проверить отображение списка объявлений.
6. Зарегистрировать нового пользователя или войти существующим.
7. Загрузить аватарку пользователя.
8. Создать объявление с изображением.
9. Открыть карточку объявления.
10. Добавить комментарий.
11. Проверить редактирование и удаление своих объявлений и комментариев.

## Примечания

- Для корректной работы frontend должен обращаться к backend на `http://localhost:8080`.
- Для работы с изображениями директория `uploads` должна быть доступна приложению на запись.
- Директория `uploads` не должна попадать в Git.
