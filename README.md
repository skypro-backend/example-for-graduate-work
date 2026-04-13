# Платформа объявлений (STOLB)

## О проекте

**STOLB** - это бэкенд-часть веб-приложения для размещения и управления объявлениями.
Проект разработан в рамках учебного курса и представляет собой REST API сервис,
аналогичный популярным платформам (Avito, Юла).

### Основной функционал:
- Регистрация и аутентификация пользователей (Basic Auth)
- Управление объявлениями (CRUD операции)
- Добавление и управление комментариями
- Загрузка и отображение изображений (аватары, фото объявлений)
- Разграничение прав доступа (USER / ADMIN)

---

## Используемые технологии

| Категория            | Технологии |
|----------------------|------------|
| **Язык**             | Java 17 |
| **Фреймворк**        | Spring Boot 3.1.0 |
| **Безопасность**     | Spring Security 6.1.0 (Basic Auth) |
| **Фронтенд часть**   | React |
| **База данных**      | PostgreSQL 42.6.0, Hibernate 6.2.2 |
| **Маппинг**          | MapStruct 1.5.5, Lombok 1.18.26 |
| **Документация API** | OpenAPI 3.0, Swagger UI (springdoc-openapi 2.1.0) |
| **Сборка**           | Maven |
| **Тестирование**     | Postman |

---

## Быстрый старт
### Предварительные требования
| Требование     | Версия |
|----------------|--------|
| **Java**       | 17+    |
| **Maven**      | 4.0.0  |
| **PostgreSQL** | 14+    |
| **Docker**     | 4.65.0 |

### Шаг 1: Клонирование репозитория
```bash
git clone https://github.com/skypro-backend/example-for-graduate-work
cd project-name
```

### Шаг 2: Установка Docker для фронта
#### Для проекта создана фронтенд-часть сайта:
- Потребуется установка Docker Desktop на ваш компьютер ==> https://www.docker.com/products/docker-desktop/
  
#### Чтобы запустить фронтенд с помощью установленного Docker, нужно открыть командную строку (или терминал) и выполнить следующую команду:
```bash
docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21
```
#### После выполнения команды frontend запустится на порту 3000 и можно будет зайти на него через браузер по адресу: http://localhost:3000

### Шаг 3: Создание базы данных PostgreSQL и установка инструментов работы с ней
#### Cсылки на скачивание инстурментов для работы с базой данных:
- установка PostgreSQL ===> https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
- установка DBeaver для визуального удобства пользования ===> https://dbeaver.io/download/;

#### Подключитесь к PostgreSQL и выполните:
```bash
CREATE DATABASE 'your_database';
CREATE USER your_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE 'your_database' TO 'your_password';
```

### Шаг 4: Настройка application.properties
Создайте файл(если его по какой-то причине нету) по пути ***src/main/resources/application.properties*** и вставте эти данные:
```bash
- spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
- spring.datasource.username=your_user
- spring.datasource.password=your_password
- spring.datasource.driver-class-name=org.postgresql.Driver
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=false
- server.port=8080
```
### Шаг 5: Создание папок для изображений
#### В корне проекта создайте папки:
- images/avatars
- images/ads

### Шаг 6: Сборка и запуск
```bash
- mvn clean install
- mvn spring-boot:run
```

### Шаг 7: Проверка
| **Сервис**     | **URL**    |
|----------------|------------|
| **Swagger UI** | **http://localhost:8080/swagger-ui.html** |
| **OpenAPI JSON**     | **https://github.com/BizinMitya/front-react-avito/blob/v1.19/openapi.yaml**    |

## API Эндпоинты

### Пользователи
|**POST /register**| **- регистрация**      |
|----------------|------------------------|
| **POST /login**| **- вход**             |
| **GET /users/me**| **- получить профиль** |
| **PATCH /users/me| **- обновить профиль** |
| PATCH /users/me/image| **- загрузить аватар** |

### Объявления
| **GET /ads**       | **- все объявления**      |
|--------------------|---------------------------|
| **GET /ads/{id}**  | **- объявление по ID**    |
| **POST /ads**      | **- создать объявление**  |
| **PATCH  /ads/{id}** | **- обновить объявление** |
| **DELETE /ads/{id}** | **- удалить объявление**  |
| **GET /ads/me**    | **- мои объявления** |

### Комментарии
| **GET    /ads/{id}/comments**               | **- комментарии объявления** |
|---------------------------------------------|------------------------------|
| **POST   /ads/{id}/comments**               | **- добавить комментарий**   |
| **PATCH  /ads/{adId}/comments/{commentId}** | **- обновить комментарий**   |
| **DELETE /ads/{adId}/comments/{commentId}** | **- удалить комментарий**    |


## Структура проекта
```plaintext
STOOLB/
├── src/main/java/ru/skypro/homework/
│   ├── controller/     - REST контроллеры
│   ├── service/        - бизнес-логика
│   ├── repository/     - работа с БД
│   ├── entity/         - сущности
│   ├── dto/            - объекты передачи данных
│   ├── mapper/         - преобразование Entity <-> DTO
│   ├── config/         - настройки (Security, Swagger)
│   └── exception/      - обработка ошибок
├── images/
│   ├── avatars/        - аватары пользователей
│   └── ads/            - картинки объявлений
└── pom.xml
```

## Автор
Ершов Илья