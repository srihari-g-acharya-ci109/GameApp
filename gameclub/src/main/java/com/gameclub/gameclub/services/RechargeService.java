package com.gameclub.gameclub.services;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.RechargeDto;
import com.gameclub.gameclub.model.Recharge;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.model.CollectionsDaily;
import com.gameclub.gameclub.repository.RechargeRepository;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.repository.CollectionsRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.exceptions.BusinessException;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepo;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private CollectionsRepository collectionsRepo;

    public Recharge create(RechargeDto dto) {
        if (dto.getAmount() <= 0) {
            throw new BusinessException("Recharge amount must be positive");
        }

        Optional<Member> optionalMember = memberRepo.findById(dto.getMemberId());
        if (!optionalMember.isPresent()) {
            throw new IdNotPresentException("Member not found: " + dto.getMemberId());
        }

        Member member = optionalMember.get();
        member.setBalance(member.getBalance() + dto.getAmount());
        memberRepo.save(member);

        Recharge recharge = new Recharge();
        recharge.setMemberId(dto.getMemberId());
        recharge.setAmount(dto.getAmount());
        recharge.setDate(LocalDateTime.now());
        Recharge savedRecharge = rechargeRepo.save(recharge);

        // --- Update CollectionsDaily ---
        LocalDate today = LocalDate.now();
        Optional<CollectionsDaily> optionalCollection = collectionsRepo.findByDate(today);
        CollectionsDaily collection;
        if (optionalCollection.isPresent()) {
            collection = optionalCollection.get();
        } else {
            collection = new CollectionsDaily();
            collection.setDate(today);
            collection.setAmount(0);
        }

        collection.setAmount(collection.getAmount() + dto.getAmount());
        collectionsRepo.save(collection);

        return savedRecharge;
    }

    public List<Recharge> findAll() {
        return rechargeRepo.findAll();
    }

    public Recharge findById(String id) {
        Optional<Recharge> optionalRecharge = rechargeRepo.findById(id);
        if (!optionalRecharge.isPresent()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }
        return optionalRecharge.get();
    }

    public Recharge update(String id, RechargeDto dto) {
        Optional<Recharge> optionalRecharge = rechargeRepo.findById(id);
        if (!optionalRecharge.isPresent()) {
            throw new IdNotPresentException("Recharge not found: " + id);
        }

        Recharge existing = optionalRecharge.get();
        existing.setAmount(dto.getAmount());
        existing.setMemberId(dto.getMemberId());
        existing.setDate(dto.getDate());
        return rechargeRepo.save(existing);
    }
}
