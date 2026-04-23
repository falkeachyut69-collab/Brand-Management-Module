package com.example.groupmanagement.repository;

import com.example.groupmanagement.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Long> {

    List<Zone> findByBrand_BrandId(Long brandId);
}