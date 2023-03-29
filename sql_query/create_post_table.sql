create table post(
	id bigint primary key auto_increment,
    creator_id bigint not null,
    title varchar(255) not null,
    content varchar(255) not null,
    constraint member_postfk foreign key (creator_id) references member(id) on update cascade on delete cascade
);