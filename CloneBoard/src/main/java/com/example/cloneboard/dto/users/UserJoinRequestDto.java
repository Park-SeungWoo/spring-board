package com.example.cloneboard.dto.users;

import com.example.cloneboard.entity.UserEntity;
import lombok.Getter;

/*
requestbody에 json으로 객체가 들어오면
여러개의 HttpMessageConverter중에서 MappingJackson2HttpMessageConverter 를 사용해
jackson이 object의 getter나 setter의 이름을 본 후 get, set부분을 지운 후 뒤의 field name을 소문자로 바꿔 나온 이름을 통해 field에 접근한다.
jackson 내부적으로 field의 이름을 알아내고 저장한 후에 ObjectMapper를 생성해서 json을 객체로 변환한다.
이후에 아까 찾은 field에 생성자나 setter를 통해 field에 값을 할당해주는게 아니라 결국 reflection 기법을 사용해 할당해준다.
https://blogshine.tistory.com/446
다들 기본 생성자는 있어야한다는데 왜 없어도 되는거지?

reflection은 spring의 DI, 즉 의존성 주입을 해줄 때에 사용되는데 java api로 제공된다.
reflection은 구체적인 클래스 타입을 몰라도, 클래스의 정보(메서드, 필드, 타입)에 접근 할 수 있게 해줌.
컴파일 시간이 아닌 실행 시간에 동적으로 접근.
https://velog.io/@suyeon-jin/%EB%A6%AC%ED%94%8C%EB%A0%89%EC%85%98-%EC%8A%A4%ED%94%84%EB%A7%81%EC%9D%98-DI%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%8F%99%EC%9E%91%ED%95%98%EB%8A%94%EA%B1%B8%EA%B9%8C
 */
@Getter
public class UserJoinRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String introduce;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .nickname(this.nickname)
                .introduce(this.introduce)
                .postSequence(1L)
                .build();
    }
}
