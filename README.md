# 이 레포지토리는 스프링을 공부하기 위한 공간입니다.

## 진행중인 project
기존에 [board_test](https://github.com/Park-SeungWoo/spring-board/tree/main/board_test)를 이용해 공부중이었으나 새로 [CloneBoard](https://github.com/Park-SeungWoo/spring-board/tree/main/CloneBoard) 프로젝트를 만들어 다시 실습중입니다.

## 이론, 내용 정리 노션
[노션(read-only)](https://spiky-house-f8d.notion.site/Spring-2a22644edb5245d5b8b84fc9c523cc94) <br/>
아직 정리하지 않은 내용이 많습니다. <br/>
추후에 하나씩 정리해서 올릴 예정입니다.

## 게시판 구조
현재 간단한 로그인, 유저 생성, 게시판 CRUD까지 구현 한 상태입니다.<br/>
게시판은 전체 글에 대한 id값과 user별 게시글 id값이 존재합니다. user별 id는 user table의 postSequence값을 하나씩 증가시키며 진행합니다. 게시글의 모든 update, delete는 postId로 진행합니다<br/>
다음 업데이트에서 로그인을 spring security, jwt을 이용해 진행할 예정입니다.<br/>
login은 sample token을 발생시켜 client에게 전달하고 게시글 관련 read를 제외한 모든 작업 시 token의 유효성 검사를 수행한 후 token에 들어있는 user정보와 접근하려는 게시판의 user정보를 비교해 같으면 접근을 허용했습니다.
이는 추후 jwt로 대체될 사항으로, 간단히 진행했습니다. 유저관련 delete 또한 token의 유효성 검사 후 request의 유저정보와 token의 정보와 비교하여 접근을 허용했습니다.<br/>
token의 유효성엔 시간 데이터는 포함하지 않고, 유저 닉네임을 포함시켜 존재여부를 확인하고, 간단한 secret키를 뒤에 붙여 확인했습니다.