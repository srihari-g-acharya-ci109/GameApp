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
    public Member saveMember(@RequestBody MemberDto dto) {
        return memberService.create(dto);
    }

    @GetMapping("/all")
    public List<Member> viewAllMembers() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable String id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable String id, @RequestBody MemberDto dto) {
        return memberService.update(id, dto);
    }
}
