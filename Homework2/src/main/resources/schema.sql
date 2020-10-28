create table genres(
    id bigserial,
    name varchar(255) unique,
    primary key (id)
);

create table authors(
    id bigserial,
    name varchar(255) unique,
    primary key (id)
);

create table books(
    id bigserial,
    title varchar(255) unique,
    genre_id bigint references genres(id),
    author_id bigint references authors(id),
    primary key (id)
);
