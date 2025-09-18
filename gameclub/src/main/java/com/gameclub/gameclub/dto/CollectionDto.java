package com.gameclub.gameclub.dto;

import java.time.LocalDate;

public class CollectionDto {
    private String id;
    private float amount;
    private LocalDate date;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
