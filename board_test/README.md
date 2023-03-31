# Spring Boot로 게시판을 만들어보기 위한 repo입니다.

## stack
- springboot, mysql, jpa

## 현재 구현 상황

rest api로 구현한 상태이고 postman으로 jpa 연동 테스트 완료.
backend를 rest api로 모두 구현한 후 front 구현 예정(flutter or react)

### spring
- 유저생성
- 유저 모두 보기
- id로 유저 찾기
- 게시글 생성
- 게시글 모두 보기
- 유저 id로 해당 유저가 게시한 글 보기

### db
- member table 생성 
```roomsql
CREATE TABLE member(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    pw VARCHAR(255) NOT NULL
);
```

- post table 생성
```roomsql
CREATE TABLE post(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    creator_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    CONSTRAINT member_postfk FOREIGN KEY (creator_id) REFERENCES member(id) ON UPDATE CASCADE ON DELETE CASCADE
);
```
ON UPDATE CASCADE: reference로 등록된 부모 데이터가 변경되면 자식 데이터도 함께 변경<br/>
ON DELETE CASCADE: 부모 데이터 삭제시 자식 데이터 함께 삭제


## 구조
동작 구조 도식화 후 업로드 예정 <br/>

[//]: # (![structure]&#40;./assets/structure.png&#41;)
