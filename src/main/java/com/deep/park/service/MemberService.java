package com.deep.park.service;

import com.deep.park.dao.MemberRepository;
import com.deep.park.dto.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void join(Member member){
        memberRepository.save(member);
    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOneMember(Long id){
        return memberRepository.findById(id);
    }
}
