create table if not exists FILMS
(
    ID            INTEGER auto_increment,
    NAME          CHARACTER LARGE OBJECT not null,
    DESCRIPTION   CHARACTER VARYING(200) not null,
    RELEASEDATE   DATE                   not null,
    DURATION      INTEGER                not null,
    RATE          INTEGER,
    ID_MPA        INTEGER                not null,
    constraint FILMS_KEY_ID
        primary key (ID)
);


create table if not exists USERS
(
    ID      INTEGER auto_increment,
    EMAIL   CHARACTER VARYING(100) not null,
    NAME    CHARACTER VARYING(100) not null,
    LOGIN   CHARACTER VARYING(100) not null,
    BIRTHDAY DATE                   not null,
    constraint USERS_KEY_ID
        primary key (ID)
);

create table if not exists FRIENDS
(
    ID_USERS       INTEGER not null references USERS,
    ID_FRIENDS     INTEGER not null references USERS,
    PRIMARY KEY (ID_USERS, ID_FRIENDS)
);

create table if not exists MPA
(
    ID_MPA          INTEGER PRIMARY KEY,
    NAME          varchar(200)

);

create table if not exists FILMLIKES
(
    ID_FILM           INTEGER,
    ID_USERS INTEGER,
    constraint foreign_key_filmlikes_id_users
        foreign key (ID_USERS) references USERS (ID),
    constraint foreign_key_filmlikes_id_films
        foreign key (ID_FILM) references FILMS (ID)

);

create table if not exists GENRES
(
    ID_GENRE       INTEGER PRIMARY KEY ,
    NAME          text not null

);

create table if not exists genresForOneFilm
(
    ID_FILM            INTEGER,
    ID_GENRES          INTEGER

);

alter table FILMS
    add constraint foreign_key_films_rating_id
        foreign key (ID_MPA) references MPA;

alter table GENRESFORONEFILM
    add constraint foreign_key_genres_id_genre
        foreign key (ID_GENRES) references GENRES;

alter table GENRESFORONEFILM
    add constraint foreign_key_genres_id_films
        foreign key (ID_FILM) references FILMS;
