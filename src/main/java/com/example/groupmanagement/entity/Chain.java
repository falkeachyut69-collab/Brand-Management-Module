package com.example.groupmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chains")
public class Chain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chainId;

    private String companyName;

    private String gstNumber;

   @ManyToOne
@JoinColumn(name = "group_id")
@JsonIgnoreProperties({"chains"})  // prevents loop
private Group group;

    private boolean isActive = true;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // GETTERS & SETTERS

    public Long getChainId() {
        return chainId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}