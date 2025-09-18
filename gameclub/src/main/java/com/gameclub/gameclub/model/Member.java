package com.gameclub.gameclub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {
    @Id
    private String id;
    private String name;
    private String phNo;
    private float balance;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhNo() {
        return phNo;
    }
    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
}
