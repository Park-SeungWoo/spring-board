desc post;
show indexes in post;
-- select * from information_schema.table_constraints;
select * from member;
select * from post;

insert into post(creator_id, title, content) values(1, "hello", "first post");
insert into post(creator_id, title, content) values(1, "hello", "second post");
insert into post(creator_id, title, content) values(2, "HI", "my first post");


select m.id, m.name, p.id as "post_id" , p.title, p.content
from member m
	inner join post p
    on m.id = p.creator_id;
-- where m.id = 1;


delete from member 
	where id = 4;
    
alter table post drop constraint member_postfk;
alter table post add constraint member_postfk foreign key (creator_id) references member(id) on update cascade on delete cascade;

delete from post
	where id = 5;
    
insert into post(creator_id, title, content) values(4, "hh", "dhdhdldl"); -- error: creator_id를 member의 id와 foreign key로 등록해둬서 member에 creator_id를 가진 column이 없으면 생성안됨.

alter table post modify creator_id bigint not null;