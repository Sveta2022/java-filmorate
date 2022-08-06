MERGE INTO  GENRES (ID_GENRE, NAME)
values (1, 'Комедия');

MERGE INTO GENRES (ID_GENRE, NAME)
values (2, 'Драма');

MERGE INTO GENRES (ID_GENRE, NAME)
values (3, 'Мультфильм');

MERGE INTO GENRES (ID_GENRE, NAME)
values (4, 'Триллер');

MERGE INTO GENRES (ID_GENRE, NAME)
values (5, 'Документальный');

MERGE INTO GENRES (ID_GENRE, NAME)
values (6, 'Боевик');

//'у фильма нет возрастных ограничений'
MERGE INTO  MPA(ID_MPA, NAME)
values (1, 'G');

// 'детям рекомендуется смотреть фильм с родителями'
MERGE INTO MPA(ID_MPA, NAME)
values (2, 'PG');

// 'детям до 13 лет просмотр не желателен'
MERGE INTO MPA(ID_MPA, NAME)
values (3, 'PG-13');

//'лицам до 17 лет просматривать фильм можно только в присутствии взрослого'
MERGE INTO MPA(ID_MPA, NAME)
values (4, 'R');

//'лицам до 18 лет просмотр запрещён'
MERGE INTO MPA(ID_MPA, NAME)
values (5, 'NC-17');





