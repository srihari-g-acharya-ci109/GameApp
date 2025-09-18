package com.gameclub.gameclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gameclub.gameclub.dto.RechargeDto;
import com.gameclub.gameclub.model.Recharge;
import com.gameclub.gameclub.services.RechargeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/recharges")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping("/save")
    public Recharge saveRecharge(@RequestBody RechargeDto dto) {
        Recharge recharge = new Recharge();
        recharge.setMemberId(dto.getMemberId());
        recharge.setAmount(dto.getAmount());
        return rechargeService.create(recharge);
    }

    @GetMapping("/all")
    public List<Recharge> viewAllRecharges() {
        return rechargeService.findAll();
    }

    @GetMapping("/{id}")
    public Recharge getRechargeById(@PathVariable String id) {
        return rechargeService.findById(id);
    }
}
