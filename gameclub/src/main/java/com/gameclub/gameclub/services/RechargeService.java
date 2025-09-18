package com.gameclub.gameclub.services;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.RechargeDto;
import com.gameclub.gameclub.model.Recharge;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.repository.RechargeRepository;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.exceptions.BusinessException;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepo;

    @Autowired
    private MemberRepository memberRepo;

    public Recharge create(RechargeDto dto) {
        if (dto.getAmount() <= 0) {
            throw new BusinessException("Recharge amount must be positive");
        }

        Member member = memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new IdNotPresentException("Member not found: " + dto.getMemberId()));
        member.setBalance(member.getBalance() + dto.getAmount());
        memberRepo.save(member);

        Recharge recharge = new Recharge();
        recharge.setMemberId(dto.getMemberId());
        recharge.setAmount(dto.getAmount());
        recharge.setDate(LocalDateTime.now());
        return rechargeRepo.save(recharge);
    }

    public List<Recharge> findAll() {
        return rechargeRepo.findAll();
    }

    public Recharge findById(String id) {
        return rechargeRepo.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Recharge not found: " + id));
    }

    public Recharge update(String id, RechargeDto dto) {
        Recharge existing = findById(id);
        existing.setAmount(dto.getAmount());
        existing.setMemberId(dto.getMemberId());
        existing.setDate(dto.getDate());
        return rechargeRepo.save(existing);
    }
}
