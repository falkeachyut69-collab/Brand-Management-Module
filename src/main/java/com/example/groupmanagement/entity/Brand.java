package com.example.groupmanagement.entity;

import com.example.groupmanagement.entity.Chain;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId; 

    @Column(length = 50)
    private String brandName;

    @ManyToOne
    @JoinColumn(name = "chain_id")
    private Chain chain;

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

    
    // Getters & Setters

public Long getBrandId() {
    return brandId;
}

public void setBrandId(Long brandId) {
    this.brandId = brandId;
}

public String getBrandName() {
    return brandName;
}

public void setBrandName(String brandName) {
    this.brandName = brandName;
}

public Chain getChain() {
    return chain;
}

public void setChain(Chain chain) {
    this.chain = chain;
}

public boolean isActive() {
    return isActive;
}

public void setActive(boolean active) {
    isActive = active;
}

public LocalDateTime getCreatedAt() {
    return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
}

public LocalDateTime getUpdatedAt() {
    return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
}
}