package com.gameclub.gameclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "collections")
public class CollectionsDaily {
    @Id
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
