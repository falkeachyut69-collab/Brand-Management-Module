package com.example.groupmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zoneId;

    private String zoneName;

    // 🔥 RELATION WITH BRAND
 @ManyToOne
@JoinColumn(name = "brand_id")
private Brand brand;
}