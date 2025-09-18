package com.gameclub.gameclub.dto;

import java.time.LocalDateTime;

public class TransactionDto {
    private String id;
    private String gameId;
    private String memberId;
    private float price;
    private LocalDateTime dateTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
