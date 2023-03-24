package com.deep.park.controller;

import com.deep.park.dto.Member;
import com.deep.park.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    MemberService memberService;

    @Autowired
    public MainController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/members")
    @ResponseBody
    public Member joinMember(@RequestHeader Map<String, String> header){
        Member m = new Member();
        m.setId(Long.parseLong(header.get("id")));
        m.setName(header.get("name"));

        memberService.join(m);

        System.out.println(m + "saved");

        return m;
    }

    @GetMapping("/members")
    @ResponseBody
    public List<Member> findAllMember(){
        return memberService.findAllMember();
    }

    @GetMapping("/members/{id}")
    @ResponseBody
    public Optional<Member> findOneById(@PathVariable("id") Long id){
        return memberService.findOneMember(id);
    }
}
