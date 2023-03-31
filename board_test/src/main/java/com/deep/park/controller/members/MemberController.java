package com.deep.park.controller.members;

import com.deep.park.dto.Member;
import com.deep.park.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/")
    public void joinMember(@RequestHeader Map<String, String> header) {
        Member m = new Member();
        m.setName(header.get("name"));
        m.setPw(header.get("pw"));

        memberService.join(m);
    }

    @GetMapping("/")
    public List<Member> findAllMember() {
        return memberService.findAllMember();
    }

    @GetMapping("/{id}")
    public Optional<Member> findOneById(@PathVariable("id") Long id) {
        return memberService.findOneMember(id);
    }

}
