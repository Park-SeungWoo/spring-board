use potato;
truncate users;
truncate boards;
insert into users(email, introduce, nickname, password, post_sequence) values("test1@naver.com", "Hi i'm test1", "test1", "test1", 1);
insert into users(email, introduce, nickname, password, post_sequence) values("test2@naver.com", "Hi i'm test2", "test2", "test2", 1);

insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 1, "1");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 2, "2");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 3, "3");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 4, "4");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 5, "5");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 6, "6");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 7, "7");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 8, "8");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 9, "9");
insert into boards(content, nickname, post_id, title) values("this is a post", "test1", 10, "10");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 1, "1");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 2, "2");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 3, "3");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 4, "4");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 5, "5");
insert into boards(content, nickname, post_id, title) values("this is a post", "test2", 6, "6");

update users set post_sequence=11 where id=1;
update users set post_sequence=7 where id=2;