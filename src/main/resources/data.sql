MERGE INTO  genre (GENRE_ID, genre)
values (1, 'Комедия');

MERGE INTO genre (GENRE_ID, genre)
values (2, 'Драма');

MERGE INTO genre (GENRE_ID, genre)
values (3, 'Мультфильм');

MERGE INTO genre (GENRE_ID, genre)
values (4, 'Триллер');

MERGE INTO genre (GENRE_ID, genre)
values (5, 'Документальный');

MERGE INTO genre (GENRE_ID, genre)
values (6, 'Боевик');

MERGE INTO rating (rating_id, ratingmpa)
values ('G', 'у фильма нет возрастных ограничений');

MERGE INTO rating (rating_id, ratingmpa)
values ('PG', 'детям рекомендуется смотреть фильм с родителями');

MERGE INTO rating (rating_id, ratingmpa)
values ('PG-13', 'детям до 13 лет просмотр не желателен');

MERGE INTO rating (rating_id, ratingmpa)
values ('R', 'лицам до 17 лет просматривать фильм можно только в присутствии взрослого');

MERGE INTO rating (rating_id, ratingmpa)
values ('NC-17', 'лицам до 18 лет просмотр запрещён');





