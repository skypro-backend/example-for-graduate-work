Создание BackEnd части сайта
Бэкенд-часть проекта предполагает реализацию следующего функционала:

Авторизация и аутентификация пользователей.
Распределение ролей между пользователями: пользователь и администратор.
CRUD-операции для объявлений и комментариев: администратор может удалять или редактировать все объявления и комментарии, а пользователи — только свои.
Возможность для пользователей оставлять комментарии под каждым объявлением.
Показ и сохранение картинок объявлений, а также аватарок пользователей.
Язык и Окружение
Java 17
Maven
Spring Boot
Spring Web
Spring Data JPA
Spring Security
Rest
Git
Swagger
Lombok
Liquibase
Docker
Установка
Для запуска приложения вам потребуется:

Установить Docker Desctop.
Клонировать репозиторий и создать проект в Intellej IDEA.
Запустить Docker Desctop.
В терминале Intellej IDEA запустить команду: docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.21
После выполнения этих шагов ваше приложение будет доступно по адресу http://localhost:3000/.
Авторы:
Лаптева Оксана
Шашкин Артём
