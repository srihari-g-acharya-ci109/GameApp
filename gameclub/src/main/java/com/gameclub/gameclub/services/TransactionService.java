package com.gameclub.gameclub.services;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.TransactionDto;
import com.gameclub.gameclub.model.TransactionRecord;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.model.Game;
import com.gameclub.gameclub.repository.TransactionRepository;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.repository.GameRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.exceptions.InsufficientBalanceException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private GameRepository gameRepo;

    public TransactionRecord create(TransactionDto dto) {
        Member member = memberRepo.findById(dto.getMemberId())
                .orElseThrow(() -> new IdNotPresentException("Member not found: " + dto.getMemberId()));
        Game game = gameRepo.findById(dto.getGameId())
                .orElseThrow(() -> new IdNotPresentException("Game not found: " + dto.getGameId()));

        if (member.getBalance() < game.getPrice()) {
            throw new InsufficientBalanceException("Not enough balance to buy game");
        }

        member.setBalance(member.getBalance() - game.getPrice());
        memberRepo.save(member);

        TransactionRecord transaction = new TransactionRecord();
        transaction.setGameId(dto.getGameId());
        transaction.setMemberId(dto.getMemberId());
        transaction.setPrice(game.getPrice());
        transaction.setDateTime(LocalDateTime.now());
        return transactionRepo.save(transaction);
    }

    public List<TransactionRecord> findAll() {
        return transactionRepo.findAll();
    }

    public TransactionRecord findById(String id) {
        return transactionRepo.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Transaction not found: " + id));
    }

    public TransactionRecord update(String id, TransactionDto dto) {
        TransactionRecord existing = findById(id);
        existing.setGameId(dto.getGameId());
        existing.setMemberId(dto.getMemberId());
        existing.setPrice(dto.getPrice());
        existing.setDateTime(dto.getDateTime());
        return transactionRepo.save(existing);
    }
}
