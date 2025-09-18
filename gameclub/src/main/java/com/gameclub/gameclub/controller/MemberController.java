package com.gameclub.gameclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gameclub.gameclub.dto.MemberDto;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.services.MemberService;

@RestController
@CrossOrigin("*")
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/save")
    public Member saveMember(@RequestBody MemberDto memberDto) {
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setPhNo(memberDto.getPhoneNumber());
        member.setBalance(memberDto.getBalance());
        return memberService.create(member);
    }

    @GetMapping("/all")
    public List<Member> viewAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.findById(id);
    }
}
