# java-filmorate
Template repository for Filmorate project.

![Untitled (3)](https://user-images.githubusercontent.com/100937559/182045211-2c4e5c7d-adaa-48a0-8c3f-20e2ecc70340.png)

[Link](https://dbdiagram.io/d/62e4ecd5f31da965e844d8e4)

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
