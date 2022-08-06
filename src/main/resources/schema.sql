create table if not exists FILMS
(
    ID            INTEGER auto_increment,
    NAME          CHARACTER LARGE OBJECT not null,
    DESCRIPTION   CHARACTER VARYING(200) not null,
    RELEASEDATE   DATE                   not null,
    DURATION      INTEGER                not null,
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
    ID_USERS       INTEGER not null,
    ID_FRIENDS     INTEGER not null,
    constraint FRIENDS_FOREIGN_KEY_ID_USERS
        foreign key (ID_USERS) references USERS(id),
    constraint FRIENDS_FOREIGN_KEY_ID_FILMS
        foreign key (ID_FRIENDS) references USERS(id)
);

create table if not exists MPA
(
    ID_MPA          INTEGER,
    NAME          varchar(200),
    constraint RATING_KEY_ID
        primary key (ID_MPA)

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
    ID_GENRE       VARCHAR(15) not null,
    NAME          text not null,
    constraint GENRE_KEY_ID
        primary key (ID_GENRE)

);

create table if not exists genresForOneFilm
(
    ID_FILM           INTEGER not null,
    ID_GENRES          INTEGER

);

alter table FILMS
    add constraint if not exists foreign_key_films_rating_id
        foreign key (ID_MPA) references MPA (ID_MPA);

alter table GENRESFORONEFILM
    add constraint if not exists foreign_key_genres_id_genre
        foreign key (ID_GENRES) references GENRES (ID_GENRE);

alter table GENRESFORONEFILM
    add constraint if not exists foreign_key_genres_id_films
        foreign key (ID_FILM) references FILMS (ID);
