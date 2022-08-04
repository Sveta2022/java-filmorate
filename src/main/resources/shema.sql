create table IF NOT EXISTS FILMS
(
    ID            INTEGER auto_increment,
    NAME          CHARACTER LARGE OBJECT not null,
    DESCRIPTION   CHARACTER VARYING(200) not null,
    "releaseDate" DATE                   not null,
    DURATION      INTEGER                not null,
    GENRE_ID      INTEGER                not null,
    RATING_ID     INTEGER                not null,
    constraint FILMS_KEY_ID
        primary key (ID)
);


create table IF NOT EXISTS USERS
(
    ID      INTEGER auto_increment,
    EMAIL   CHARACTER VARYING(100) not null,
    NAME    CHARACTER VARYING(100) not null,
    LOGIN   CHARACTER VARYING(100) not null,
    BIRTHDAY DATE                   not null,
    constraint USERS_KEY_ID
        primary key (ID)
);

create table IF NOT EXISTS FRIENDS
(
    ID_USERS       INTEGER not null,
    ID_FRIENDS     INTEGER not null,
    constraint FRIENDS_FOREIGN_KEY_ID_USERS
        foreign key (ID_USERS) references USERS(id),
    constraint FRIENDS_FOREIGN_KEY_ID_FILMS
        foreign key (ID_FRIENDS) references USERS(id)
);

create table IF NOT EXISTS RATING
(
    rating_id            VARCHAR(5) not null,
    ratingMPA          varchar(200) not null,
    constraint RATIND_KEY_ID
        primary key (rating_id)
);

create table IF NOT EXISTS FILMLIKES
(
    id_film            INTEGER,
    id_users INTEGER,
    constraint foreign_key_filmlikes_id_users
        foreign key (id_users) references USERS (id),
    constraint foreign_key_filmlikes_id_films
        foreign key (id_film) references FILMS (id)

);

create table IF NOT EXISTS GENRE
(
    genre_id       INTEGER not null auto_increment,
    genre          text not null,
    constraint GENRE_KEY_ID
        primary key (genre_id)
);

create table IF NOT EXISTS genresForOneFilm
(
    id_film           INTEGER not null,
    id_genre          INTEGER,
    constraint foreign_key_genresForOneFilm_id_genre
        foreign key (id_genre) references GENRE (GENRE_ID)
);

alter table FILMS
    add constraint IF NOT EXISTS foreign_key_films_rating_id
        foreign key (rating_id) references RATING (rating_id);

alter table GENRESFORONEFILM
    add constraint IF NOT EXISTS foreign_key_genres_id_genre
        foreign key (id_genre) references GENRE (genre_id);

alter table GENRESFORONEFILM
    add constraint IF NOT EXISTS foreign_key_genres_id_films
        foreign key (id_film) references FILMS (id);
