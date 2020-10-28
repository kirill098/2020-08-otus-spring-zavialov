insert into genres(name) values ('genre1');
insert into genres(name) values ('genre2');

insert into authors(name) values ('author1');
insert into authors(name) values ('author2');

insert into books(title, genre_id, author_id) values ('title1', 1, 1);
insert into books(title, genre_id, author_id) values ('title2', 1, 2);
insert into books(title, genre_id, author_id) values ('title3', 2, 1);
insert into books(title, genre_id, author_id) values ('title4', 2, 2);