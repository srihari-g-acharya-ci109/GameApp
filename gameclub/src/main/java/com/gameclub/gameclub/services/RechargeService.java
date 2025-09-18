package com.gameclub.gameclub.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.exceptions.BusinessException;
import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.model.Recharge;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.repository.RechargeRepository;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository repo;

    @Autowired
    private MemberRepository memberRepo;

    public Recharge create(Recharge recharge) {
        if (recharge.getAmount() <= 0) {
            throw new BusinessException("Recharge amount must be positive");
        }

        Optional<Member> optionalMember = memberRepo.findById(recharge.getMemberId());
        if (optionalMember.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + recharge.getMemberId());
        }

        Member member = optionalMember.get();
        member.setBalance(member.getBalance() + recharge.getAmount());
        memberRepo.save(member);

        recharge.setId(null);
        recharge.setDate(LocalDateTime.now());
        return repo.save(recharge);
    }

    public List<Recharge> findAll() {
        return repo.findAll();
    }

    public Recharge findById(String id) {
        Optional<Recharge> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        return optional.get();
    }

    public Recharge update(String id, Recharge recharge) {
        Optional<Recharge> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        Recharge old = optional.get();
        old.setAmount(recharge.getAmount());
        old.setDate(recharge.getDate());
        old.setMemberId(recharge.getMemberId());
        return repo.save(old);
    }

    public boolean delete(String id) {
        Optional<Recharge> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
