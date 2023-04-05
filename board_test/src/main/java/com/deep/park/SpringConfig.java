package com.deep.park;

import com.deep.park.dao.MemberRepository;
import com.deep.park.service.MemberService;
import com.deep.park.test.Crypto;
import com.deep.park.test.MainCrypto;
import com.deep.park.test.Sha512;
import com.deep.park.test.Strong;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemberRepository();
    }

    @Bean
    public Crypto cryptoSha(){
        return new Sha512();
    }

    @Bean
    @Qualifier("mainCrypto")
    public Crypto cryptoStrong(){
        return new Strong();
    }
}
