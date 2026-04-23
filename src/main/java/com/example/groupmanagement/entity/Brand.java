package com.example.groupmanagement.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    private String brandName;

 @ManyToOne
@JoinColumn(name = "chain_id")
@JsonIgnoreProperties({"brands"})  // prevents loop
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

    // GETTERS & SETTERS

    public Long getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public Chain getChain() {
        return chain;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setChain(Chain chain) {
        this.chain = chain;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}