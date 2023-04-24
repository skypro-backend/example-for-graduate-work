
<div>

## Дипломная работа по специальности «Java-разработчик (ISA)», Skypro
</div>

___
### Разработчик
- [Николенко Павел](https://github.com/HeWwhoTalksLoud)

___
## Описание проекта и его функциональность

Данный проект — заготовка интернет-магазина, в которой можно размещать объявления 
о продаже товаров (с фотографиями) и оставлять комментарии к объявлениям других
пользователей.

### В проекте реализованы следующие функции:

- Авторизация и аутентификация пользователей;
- CRUD-операции для объявлений на сайте;
- CRUD-операции для комментариев к объявлениям;
- Пользователи могут создавать, удалять или редактировать свои собственные
объявления и комментарии. Администраторы могут удалять или редактировать
все объявления и комментарии;
- Поиск объявлений по названию в шапке сайта;
- Загрузка и отображение изображений объявлений и аватаров пользователей.

___
## Запуск приложения
* Для запуска приложения необходимо выполнить следующие действия:
    - Клонировать проект и открыть его в среде разработки (*IntelliJ IDEA*);
    - Указать путь к базе данных в файле **application.properties**;
    - Запустить **Docker**;
    - В командной строке прописать команду 
  ```docker pull ghcr.io/bizinmitya/front-react-avito:latest``` и скачать образ;
    - Запустить **Docker image** с помощью команды
  ```docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:latest```;
    - Запустить метод **main** в файле **MarketplaceApplication.java**.

После выполнения всех шагов, веб-приложение будет доступно по адресу:
http://localhost:3000

Swagger будет доступен по адресу:
http://localhost:8080/swagger-ui/index.html

___
## Используемые технологии
* **Backend**:
    - Java 11
    - Maven
    - Spring Boot
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Stream API
    - REST
    - GIT
    - Swagger
    - Lombok
* **SQL**:
    - PostgreSQL
    - Liquibase
* **Test**:
    - Junit
    - Mockito
* **Frontend**:
    - Docker image

___
## Задание
- [ТЗ на разработку](https://skyengpublic.notion.site/02df5c2390684e3da20c7a696f5d463d)