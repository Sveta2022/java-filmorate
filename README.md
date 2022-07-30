# java-filmorate
Template repository for Filmorate project.
![Contribution guidelines for this project](Untitled_00002.png)

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
