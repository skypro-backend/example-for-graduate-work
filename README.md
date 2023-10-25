# GarageSaleProject
GarageSaleProject - это место, где вы можете легко продавать или покупать товары. Вы можете разместить объявление о новом или б/у товаре. Для начала нужно создать учетную запись, а затем войти в приложение. Авторизованный пользователь может создавать и редактировать свою рекламу, изменять сведения о своем счете.

### Доступные функции:
1) Создание, изменение, удаление;
2) Сохранить, обновить, показать изображения;
3) Комментировать, изменять или удалять;
4) Показывать все доступные объявления или объявления пользователей;
5) Показать подробную информацию о выбранной рекламе;
6) Показать все комментарии к выбранному лоту.

### Безопасность
Реализуется посредством Spring Security.

#### Роли:
1) **Anonymous user** - пользователь без аутентификации имеет полный доступ для просмотра всех доступных объявлений;
2) **User** - возможность изменять или удалять только свои объявления;
3) **Admin** - возможность изменять или удалять все объявления.

### Набор технологий:
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Web Services](https://spring.io/projects/spring-ws)
* [Liquibase](https://www.liquibase.org/)
* [Hibernate](https://hibernate.org/)
* [Spring Security](https://spring.io/projects/spring-security)
* [PostgreSQL](https://www.postgresql.org/)
* [Docker](https://www.docker.com/)
* [Java SE 17](https://www.oracle.com/cis/java/technologies/javase/jdk11-archive-downloads.html)

### Действия по установке:

Установка Docker Desktop:
1) [Скачиваем Docker](https://www.docker.com/products/docker-desktop/)
2) [Устанавливаем WSL 2 для Windows](https://learn.microsoft.com/en-us/windows/wsl/install-manual)
3) [Установка Docker Desktop](https://docs.docker.com/desktop/install/windows-install/)

[Установка IntelliJ IDEA Ultimate или Community Edition](https://www.jetbrains.com/ru-ru/idea/download/?section=windows)

Создание проекта:
1) В IntelliJ IDEA: 'File -> New -> Project from Version Control'
2) Вставте URL-адрес проекта во всплывающее окно https://github.com/Makcim83/GarageSaleProject.git
3) Затем нажмите "Clone".

Выполните команду `docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.18` в терминале для запуска фронтэнда.
Фронтенд работает на: http://localhost:3000

### Команда разработки:
* Екатерина Васильева
* Александра Шакирова
* Максим Медведев
* Алексей Седов
