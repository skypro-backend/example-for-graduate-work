# Проект "Доска объявлений" 

## 📋 Назначение проекта

Веб-приложение для размещения и управления объявлениями. Пользователи могут создавать объявления, оставлять комментарии, управлять своим профилем и аватаром. Приложение разработано в рамках курса SkyPro и демонстрирует использование современных технологий Java-разработки.

## 🛠 Технологии

### Backend
- **Java 11** - основной язык программирования
- **Spring Boot 2.7.0** - фреймворк для создания приложения
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
- **Axios** - HTTP-клиент для взаимодействия с backend

## 📦 Требования к системе

### Для запуска необходимы:
- **Java 11** или выше
- **Maven 3.6+**
- **PostgreSQL 14+**
- **Node.js 14+** (для frontend)
- **npm** или **yarn** (для frontend)

## 🗄 Настройка базы данных

### 1. Установка PostgreSQL


# Ubuntu/Debian
sudo apt-get install postgresql postgresql-contrib

# Windows
# Скачайте установщик с официального сайта: https://www.postgresql.org/download/windows/

# MacOS
brew install postgresql

# Подключение к PostgreSQL
sudo -u postgres psql

# Создание базы данных
CREATE DATABASE skypro_homework;

# Создание пользователя (опционально)
CREATE USER skypro_user WITH PASSWORD 'password';

# Выдача прав
GRANT ALL PRIVILEGES ON DATABASE skypro_homework TO skypro_user;

# Выход
\q

# Настройки PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/skypro_homework
spring.datasource.username=skypro_user
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

1. Клонирование репозитория
git clone https://github.com/your-username/skypro-homework.git
cd skypro-homeworkм
2. Настройка конфигурации
 Отредактируйте файл src/main/resources/application.properties:
Укажите правильные данные для подключения к PostgreSQL
При необходимости измените порт (по умолчанию 8080)
3. Сборка проекта
   mvn clean package
4. Запуск приложения
   # Способ 1: Через Maven
mvn spring-boot:run

# Способ 2: Через JAR файл
java -jar target/homework-1.0.0.jar

# Способ 3: В IDE (IntelliJ IDEA, Eclipse)
# Запустите класс HomeworkApplication
5. Проверка запуска
   Started HomeworkApplication in X seconds (JVM running for X)

Основные возможности
Пользователи
Регистрация и авторизация

Просмотр и редактирование профиля

Загрузка аватара

Смена пароля

Объявления
Создание объявлений с картинками

Просмотр всех объявлений (без авторизации)

Просмотр детальной информации

Редактирование и удаление своих объявлений

Загрузка и обновление картинок для объявлений

Получение списка своих объявлений

Комментарии
Добавление комментариев к объявлениям

Просмотр комментариев (без авторизации)

Редактирование и удаление своих комментариев

Изображения
Загрузка изображений для аватаров

Загрузка изображений для объявлений

Автоматическое сохранение в файловой системе

Публичный доступ к изображениям

🔧 Дополнительная информация
Тестирование API с Postman
Импортируйте коллекцию (если предоставлена)

Для запросов, требующих авторизации, используйте Basic Auth:

Username: user@gmail.com или admin@mail.com

Password: password

Особенности прав доступа
USER - может управлять только своими объявлениями и комментариями

ADMIN - может управлять любыми объявлениями и комментариями

Публичные GET запросы на объявления и изображения доступны всем
