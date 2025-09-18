package com.gameclub.gameclub.dto;

import java.time.LocalDateTime;

public class RechargeDto {
    private String id;
    private String memberId;
    private float amount;
    private LocalDateTime date;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
