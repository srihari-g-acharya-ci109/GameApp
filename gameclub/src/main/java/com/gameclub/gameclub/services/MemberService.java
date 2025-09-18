package com.gameclub.gameclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public Member create(Member member) {
        member.setId(null);
        return repo.save(member);
    }

    public List<Member> findAll() {
        return repo.findAll();
    }

    public Member findById(String id) {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        return optional.get();
    }

    public Member update(String id, Member member) {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        Member old = optional.get();
        old.setName(member.getName());
        old.setPhNo(member.getPhNo());
        old.setBalance(member.getBalance());
        return repo.save(old);
    }

    public boolean delete(String id) {
        Optional<Member> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
