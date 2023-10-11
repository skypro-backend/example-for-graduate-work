# Приложение по перепродаже вещей

Приложение по перепродаже вещей - платформа, где вы можете выложить своё объявление о продаже товара: нового или б/у. Чтобы начать пользоваться приложением, нужно авторизироваться и пройти аутентификацию. После этого пользователь может просматривать свои данные, изменять их, в том числе и свой пароль. Он так же может подгрузить или изменить фотографию в свой профиль.

### Приложение выполняет ряд функций:

1) Можно добавить, изменить и удалить объявление;
2) Показывать, сохранять и изменять картинки объявлений;
3) Написать комментарий к объявлению, изменить его или удалить;
4) Получить все доступные объявления или получить все объявления одного пользователя;
5) Получить всю информацию об одном объявлении;
6) Просмотреть все комментарии к одному объявлению.

### Безопасность

Приложение использует Spring Security, распределены роли между пользователями: анонимный пользователь, пользователь, администратор:

1) **Анонимный пользователь (без прохождения аутентификаци)** - доступен просмотр всех объявлений;
2) **Пользователь** - может удалять или редактировать только свои объявления;
3) **Администратор** - может удалять или редактировать все объявления.

### Технологии:
* Hibernate https://hibernate.org/
* Spring Boot https://spring.io/projects/spring-boot
* Spring Web Services https://spring.io/projects/spring-ws
* PostgreSQL https://www.postgresql.org/
* Spring Security https://spring.io/projects/spring-security
* Liquibase https://www.liquibase.org/
* Docker https://www.docker.com/
* Java SE 11 https://www.oracle.com/cis/java/technologies/javase/jdk11-archive-downloads.html

### Использование

Установите Docker Desktop:
1) Скачайте дистрибутив:
https://www.docker.com/products/docker-desktop/
2) Установите WSL 2 на Windows:
https://learn.microsoft.com/en-us/windows/wsl/install-manual
3) После этого вы можете перейти к установке Docker Desktop:
https://docs.docker.com/desktop/install/windows-install/

Установите среду разработки (IntelliJ IDEA)
1) Платная версия https://www.jetbrains.com/idea/
2) Бесплатная версия (IntelliJ IDEA Community Edition) https://www.jetbrains.com/ru-ru/idea/download/?section=windows

Скопируйте наш проект в среду разработки:
1) Выполните команду: File -> New -> Project from Version Control 
2) В открывшемся окне в поле URL вставьте наш проект https://github.com/AleksandrZhukovJava/3S.git
3) Нажмите Clone.

Чтобы запустить фронтенд с помощью установленного Docker, нужно открыть командную строку (или терминал) и выполнить следующую команду:
docker run -p 3000:3000 --rm ghcr.io/bizinmitya/front-react-avito:v1.18

После выполнения команды frontend запустится можно будет зайти на него через браузер по адресу: http://localhost:3000

### Команда проекта
* Жуков Александр 
* Косенко Александра
* Журавская Светлана	
* Кузьмичев Игорь
