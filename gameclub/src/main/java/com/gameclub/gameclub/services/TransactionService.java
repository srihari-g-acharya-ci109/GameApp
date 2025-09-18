package com.gameclub.gameclub.services;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.exceptions.IdNotPresentException;
import com.gameclub.gameclub.exceptions.InsufficientBalanceException;
import com.gameclub.gameclub.model.Member;
import com.gameclub.gameclub.model.Game;
import com.gameclub.gameclub.model.TransactionRecord;
import com.gameclub.gameclub.repository.MemberRepository;
import com.gameclub.gameclub.repository.GameRepository;
import com.gameclub.gameclub.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private GameRepository gameRepo;

    public TransactionRecord create(TransactionRecord transaction) {
        Optional<Member> optionalMember = memberRepo.findById(transaction.getMemberId());
        if (optionalMember.isEmpty()) {
            throw new IdNotPresentException("Member not found: " + transaction.getMemberId());
        }

        Optional<Game> optionalGame = gameRepo.findById(transaction.getGameId());
        if (optionalGame.isEmpty()) {
            throw new IdNotPresentException("Game not found: " + transaction.getGameId());
        }

        Member member = optionalMember.get();
        Game game = optionalGame.get();

        if (member.getBalance() < game.getPrice()) {
            throw new InsufficientBalanceException("Not enough balance to buy game");
        }

        member.setBalance(member.getBalance() - game.getPrice());
        memberRepo.save(member);

        transaction.setId(null);
        transaction.setPrice(game.getPrice());
        transaction.setDateTime(LocalDateTime.now());
        return repo.save(transaction);
    }

    public List<TransactionRecord> findAll() {
        return repo.findAll();
    }

    public TransactionRecord findById(String id) {
        Optional<TransactionRecord> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Transaction not found: " + id);
        }
        return optional.get();
    }

    public TransactionRecord update(String id, TransactionRecord transaction) {
        Optional<TransactionRecord> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Transaction not found: " + id);
        }
        TransactionRecord old = optional.get();
        old.setGameId(transaction.getGameId());
        old.setMemberId(transaction.getMemberId());
        old.setPrice(transaction.getPrice());
        old.setDateTime(transaction.getDateTime());
        return repo.save(old);
    }

    public boolean delete(String id) {
        Optional<TransactionRecord> optional = repo.findById(id);
        if (optional.isEmpty()) {
            throw new IdNotPresentException("Transaction not found: " + id);
        }
        repo.deleteById(id);
        return true;
    }
}
