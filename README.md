# java-filmorate
Template repository for Filmorate project.

![Untitled (3)](https://user-images.githubusercontent.com/100937559/190260833-d04edb40-7e28-485a-ab9f-de2ad01ee5c2.png)




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
