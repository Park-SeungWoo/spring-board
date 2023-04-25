# 이 레포지토리는 스프링을 공부하기 위한 공간입니다.

## 진행중인 project
기존에 [board_test](https://github.com/Park-SeungWoo/spring-board/tree/main/board_test)를 이용해 공부중이었으나 새로 [CloneBoard](https://github.com/Park-SeungWoo/spring-board/tree/main/CloneBoard) 프로젝트를 만들어 다시 실습중입니다.

## 이론, 내용 정리 노션
[노션(read-only)](https://spiky-house-f8d.notion.site/Spring-2a22644edb5245d5b8b84fc9c523cc94) <br/>
아직 정리하지 않은 내용이 많습니다. <br/>
추후에 하나씩 정리해서 올릴 예정입니다.

## 게시판 구조
- 현재 간단한 로그인, 유저 생성, 게시판 CRUD까지 구현 한 상태입니다.
- 게시판은 전체 글에 대한 id값과 user별 게시글 id값이 존재합니다. user별 id는 user table의 postSequence값을 하나씩 증가시키며 진행합니다. 게시글의 모든 update, delete는 postId로 진행합니다.
- 게시글 조회는 pagination을 애용해 page=1, size=10을 default로 전체 게시글 조회, 사용자별 게시글 조회에 적용됩니다.
- 현재까지 구현한 인증, 인가는 따로 모듈을 사용하지 않고 jwt와 유사한 간단한 토큰을 만들어 해당 토큰으로 인가를 하도록 했습니다.
- 인가는 filter에서 진행해 인가된 유저 객체를 RequestAttribute로 넘겨 controller에서 받을 수 있도록 했습니다.
- 다음 업데이트에서 spring security, jwt을 이용해 진행할 예정입니다.
