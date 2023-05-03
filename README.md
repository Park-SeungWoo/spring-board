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
- 현재 JWT를 이용해 인증, 인가를 하도록 구현했습니다.
- 인증은 security의 filter chain에 custom filter를 넣어 해당 filter에서 request의 header로 들어온 jwt를 받아와 validate하고 token내부의 유저 정보를 security context에 저장하도록 구현했습니다.
- 인가는 security config에서 antmatcher를 사용해 특정 uri, method로 요청이 들어올 시 권한 체크를 하도록 했고 요청과 함께 온 토큰에 필요 권한이 있는지 여부 확인은 인증 시 security context에 유저 정보를 저장함으로써 spring security가 자동으로 context에서 꺼내와 권한 체크를 할 수 있도록 했습니다.
