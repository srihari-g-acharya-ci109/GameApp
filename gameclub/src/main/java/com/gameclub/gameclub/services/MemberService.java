package com.gameclub.gameclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.MemberDto;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepo;

    public Member create(MemberDto dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setPhNo(dto.getPhoneNumber());
        member.setBalance(dto.getBalance());
        member.setId(null);
        return memberRepo.save(member);
    }

    public List<Member> findAll() {
        return memberRepo.findAll();
    }

    public Member findById(String id) {
        return memberRepo.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Member not found: " + id));
    }

    public Member update(String id, MemberDto dto) {
        Member existing = findById(id);
        existing.setName(dto.getName());
        existing.setPhNo(dto.getPhoneNumber());
        existing.setBalance(dto.getBalance());
        return memberRepo.save(existing);
    }
}
