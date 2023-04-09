# java-filmorate
Template repository for Filmorate project.

<img src='https://github.com/Sveta2022/java-filmorate/blob/main/Схема%20базы%20данных.png'/>


SELECT name, <br>
birthday <br>
FROM users;

SELECT name, <br> 
genre <br>
FROM films;


SELECT id_users <br> 
FROM friends <br>
WHERE id_users = 1;

SELECT id_users, <br>
COUNT(id_friends) <br>
FROM friends <br>
GROUP BY id_users <br>
LIMIT 10; 
