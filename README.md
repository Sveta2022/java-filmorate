Стэк: Java 11, H2, Maven, Lombok, Spring Boot.


Filmorate работает с фильмами и оценками пользователей,  <br>
а также возвращает топ-5 фильмов, рекомендованных к просмотру.  <br>
 <br>
 <br>
Приложение JAVA-filmorate - монолитное API REST,
умеет:
 - Принимать DTO в слой REST контроллера; <br>
 - передавать данные в сервисный слой, где описана логика каждого эндпоинта;
 - сохранять данный в базу H2;
 - возвращать ответ или выбрасывать исключение. 

В Приложение описаны сущности:  <br>
Film, User, Genre, MpaRating

Приложение протестировано JUnit
 
<img src='https://github.com/Sveta2022/java-filmorate/blob/main/Схема%20базы%20данных.png'/>

1. Получить пользователей  <br>
SELECT name, <br>
birthday <br>
FROM users;

2. Получить все фильмы  <br>
SELECT name, <br> 
genre <br>
FROM films;

3. Получить пользователя по id = 1  <br>
SELECT id_users <br> 
FROM friends <br>
WHERE id_users = 1;

4. Получить 10 записей по данным Подписчики пользователя  <br>
SELECT id_users, <br>
COUNT(id_friends) <br>
FROM friends <br>
GROUP BY id_users <br>
LIMIT 10; 
