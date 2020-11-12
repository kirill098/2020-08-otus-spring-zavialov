create table books(
    id bigserial,
    title varchar(255),
    primary key (id)
);

create table genres(
    id bigserial,
    name varchar(255),
    book_id bigint references books(id) on delete cascade,
    primary key (id)
);

create table authors(
    id bigserial,
    name varchar(255),
    book_id bigint references books(id) on delete cascade,
    primary key (id)
);

create table comments(
    id bigserial,
    description varchar(255),
    book_id bigint references books(id) on delete cascade,
    primary key(id)
);