# java-filmorate
Template repository for Filmorate project.

![Untitled (3)](../../Desktop/Диаграмма.png)



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
